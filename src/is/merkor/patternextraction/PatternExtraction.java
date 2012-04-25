package is.merkor.patternextraction;

import is.merkor.util.FileCommunicatorWriting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Extracts patterns from parsed or tagged texts according to external pattern rules.
 * 
 * The implementation assumes a text parsed with IceNLP IceParser, one phrase per line.
 * It extracts noun and prepositional phrases: replace the patterns assigned to the 
 * instance variables if using another format and / or want to extract other patterns.
 * 
 * Usage:
 *  PatternExtraction extraction = new PatternExtraction(outputfilename);
 * 	for(String line : inputLines)
 * 		extraction.processLine(line);
 *  extraction.close();
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 */

public class PatternExtraction {
	
	private Set<String> distinctPatterns;
	private List<PatternInfo> extractedPatterns;
	/*
	 * Hard coded regular expressions and phrase / tag info.
	 * Replace for another format and or pattern extraction plans!
	 */
	// a patternSequence has to start with a pattern that matches this one
    private final String validStartRegex = "(\\[NPs?.+)|(\\[PP.+)|(\\[AP.+)";
    
    // matches all conj phrases and ','; simple PP: [PP í aþ PP] and simple noun phrases without a noun: [NP 4,6 ta NP]
    private final String nonValidEndRegex = "(\\[CP.+|,.*)|(\\[PP\\s+([\\S]+\\s+){2}PP\\])|(\\[NP\\s+[\\S]+\\s+[^n]+\\s+NP\\])";
    
    private final String prepPhrase = "[PP"; // start of a prepositional phrase
    private final String conjPhrase = "[CP"; // start of a conjunction phrase	
    private final String openPhrase = "[";
    private final String closePhrase = "]";
    private final String nounTag = "n";
    private final String adjTag = "l";
    
    // The input should be formatted one phrase per line,
    // patternSequence is a sequence of phrase patterns that together build one valid pattern.
    private List<PatternInfo> patternSequence = new ArrayList<PatternInfo>();
    
    public PatternExtraction () {
    	distinctPatterns = new TreeSet<String>(); // map with freqs?
    	extractedPatterns = new ArrayList<PatternInfo>();
    }
    /**
     * Extracts valid pattern from {@code line} and adds to
     * {@code patternSequence} if successful.
     * 
     * @param line
     */
    public void processLine (String line) {
    	// A start of a pattern sequence can only be according to
    	// 'validStartRegex'. For MerkOr those are [NP, [PP and [AP. 
        if (hasValidStart(line)) {
            PatternInfo pat = extractPatternFrom(line);
            patternSequence.add(pat);
        }
        // A conjunction phrase is only extracted if patternSequence
        // is not empty, to connect two valid phrases.
        else if (isConjunction(line) && (!patternSequence.isEmpty())) {
            PatternInfo pat = new PatternInfo(line, line);
            patternSequence.add(pat);
        }
        else {
            testPatternSequence();
            if (!patternSequence.isEmpty()) { 
            	addExtractedPattern();
            }
            patternSequence.clear();
        }
    }
    
    public List<PatternInfo> getExtractedPatterns() {
    	return extractedPatterns;
    }
    public List<String> getExtractedPatternsAsStrings() {
    	List<String> patterns = new ArrayList<String>();
    	for (PatternInfo info : extractedPatterns) {
    		String pattern = info.getPattern();
    		String text = info.getOriginalString();
    		patterns.add(pattern + "\t" + text);
    	}
    	return patterns;
    }
    private void addExtractedPattern() {
    	String pattern = extractCompletePattern();
    	String text = extractCompleteText();
    	extractedPatterns.add(new PatternInfo(pattern.trim(), text.trim()));
    }
    // validates a completed pattern sequence -
    // corrects it if it ends with a conjunction, clears if non valid.
    private void testPatternSequence() {
        if(patternSequence.isEmpty())
            return;
        
        eliminateNonValidPhrases();
        validateSinglePhrase();
        validatePosTags();
        validateWords();
    }
    
    // Eliminates end phrases iteratively as long as they are non valid.
    private void eliminateNonValidPhrases () {
    	if(patternSequence.size() > 1) {
            int lastIndex = patternSequence.size() - 1;
            String lastElem = patternSequence.get(lastIndex).getPattern();
            while (isNonValid(lastElem)) {
                patternSequence.remove(lastIndex);
                lastIndex = patternSequence.size() - 1;
                lastElem = patternSequence.get(lastIndex).getPattern();
            } 
        }
    }
    // a patternSequence constisting of only one phrase has
    // to be of a certain length, i.e. contain more than one
    // content word (noun, adj).
    private void validateSinglePhrase () {
    	if(patternSequence.size() == 1) {
            PatternInfo pat = patternSequence.get(0);
            String [] toks = pat.getPattern().split(" ");
            if(toks.length <= 4) {
                patternSequence.clear();
                return;
            }
            if(toks[0].equals(prepPhrase)) {
                if(toks.length <= 7) {
                    patternSequence.clear();
                    return;
                }
            }
        }
    }
    
    /*	Non valid end phrases:
     *  [CP
     *  a simple PP (has no noun: [PP í aþ PP])
     *  a simple NP without a noun ([NP 4,6 ta NP])
     */
    private boolean isNonValid (String line) {
    	return line.matches(nonValidEndRegex);
    }
    
    private void validatePosTags () {
    	if(!hasValidPosTags()){
            patternSequence.clear();
            return;
        }
    }
    private void validateWords () {
    	if (!hasValidWords()) {
            patternSequence.clear();
            return;
        }
    }
    /* patternSequence has to include at least
     * two nouns or two adjectives or
     * one noun AND one adjective:
     * 2x n || 2x l || (1x n && 1x l)
     */
    public boolean hasValidPosTags() {
        List<String> tags = new ArrayList<String>();
        int nounCount = 0;
        int adjCount = 0;
        // count nouns and adjectives
        for(PatternInfo p : patternSequence) {
            tags = p.getPOStags();
            for(String s : tags) {
                if(s.startsWith(nounTag))
                    nounCount++;
                if(s.startsWith(adjTag))
                    adjCount++;
            }
        }
        if ((nounCount > 1) || (adjCount > 1))
            return true;
        if ((nounCount >= 1) && (adjCount >= 1))
            return true;
        return false;
    }

    public boolean hasValidWords() {
        List<String> words = new ArrayList<String>();
        for(PatternInfo p : patternSequence) {
            words = p.getWords();
            for(String s : words) {
                if((s.length() == 1) && !Character.isLetterOrDigit(s.charAt(0)))
                    return false;
            }
        }
        return true;
    }
    public boolean hasValidStart (String line) {
        return line.matches(validStartRegex);
    }
    
    public boolean isConjunction (String line) {
        return line.matches(nonValidEndRegex);
    }
    
    /*
     * Extracts pattern from a parsed input line
     * Example:
     * "[PP í aþ [NP [AP fyrsta lkeþve AP] leikhluta nkeþ NP] PP]"
     * becomes:
     * "[PP í  aþ [NP[AP lkeþve ] nkeþ ]]"
     *
     * @param line input line from parsed file
     * @return PatternInfo including extracted pattern and <c>line</c>
     */
    private PatternInfo extractPatternFrom (String line) {
        String [] tokens = line.split(" ");
        String pattern = "";
        Boolean openFlag = false;
        Boolean pp_cpFlag = false;
        
        for (int i = 0; i < tokens.length; i++) {
            //check if start of a phrase
            if(tokens[i].startsWith(openPhrase)) {
                pattern = pattern.concat(tokens[i]);
                openFlag = true; //indicates an unclosed phrase
                //if PP or CP, the concrete word belongs to the pattern
                if (tokens[i].startsWith(prepPhrase) || tokens[i].startsWith(conjPhrase))
                    pp_cpFlag = true;
            }
            //check if end of a phrase, don't write the phrase name again, just close with ]
            else if (tokens[i].endsWith(closePhrase)) {
                pattern = pattern.concat(closePhrase);
                pp_cpFlag = false;
            }
            
            else {
                if (openFlag) {
                    openFlag = false;
                    // if PP or CP, write the concrete word
                    // else do nothing
                    if (pp_cpFlag) {
                        pattern = pattern.concat(" " + tokens[i] + " ");
                        pp_cpFlag = false;
                    }    
                }

                else {
                    String tags = tokens[i];
                    //eliminate genus info from pos-tags:
                    if (tags.length() >= 4) {
                        Character c;
                        Character first = tags.charAt(0);
                        switch(first) {
                            case 'n' : c = tags.charAt(1);
                                break;
                            case 'l' : c = tags.charAt(3);
                                break;
                            case 'f' : c = tags.charAt(2);
                                break;
                            case 'g' : c = tags.charAt(1);
                                break;
                            case 't' : c = tags.charAt(2);
                                break;
                            default : c = 'b';
                        }
                        tags = tags.replaceFirst(c.toString(), "x"); 
                    }
                    //add pos-tags
                    pattern = pattern.concat(" " + tags + " ");
                    openFlag = true;
                }
            }
        }
        return (new PatternInfo(pattern, line));
    }
    private String extractCompletePattern() {
    	StringBuffer buffer = new StringBuffer();
    	for (PatternInfo pat : patternSequence) {
    		buffer.append(pat.getPattern().replaceAll("\n", ""));
    		buffer.append(" ");
    	}
    	return buffer.toString();
    }
    private String extractCompleteText() {
    	StringBuffer buffer = new StringBuffer();
    	for (PatternInfo pat : patternSequence) {
    		buffer.append(pat.getOriginalString().replaceAll("\n", ""));
    		buffer.append(" ");
    	}
    	return buffer.toString();
    }    
}
