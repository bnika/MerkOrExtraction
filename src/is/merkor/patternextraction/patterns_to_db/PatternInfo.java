package is.merkor.patternextraction.patterns_to_db;

/**
 *
 * @author anna
 */

import java.util.*;

public class PatternInfo implements Comparable {
//public class PatternInfo {
    private String pattern;
    private String originalString;
    private int nrOfExamples;
    private String note;
    private String relation;
    private String matchedRegex;

    public PatternInfo() {

    }

    public PatternInfo(String pat, String orig) {
        pattern = pat;
        originalString = orig;
    }

    public PatternInfo(String pat, String orig, String regex) {
        pattern = pat;
        originalString = orig;
        matchedRegex = regex;
    }
//    @Override
//    public int compareTo(PatternInfo patInf) {
//        return pattern.compareTo(patInf.getPattern());
//    }
    
    public void setPattern(String pat) {
        pattern = pat;
    }

    public void setOrigString(String orig) {
        originalString = orig;
    }
    
    public void setNrOfExamples(int nr) {
        nrOfExamples = nr;
    }

    public void setNote(String str) {
        note = str;
    }

    public void setRelation(String rel) {
        relation = rel;
    }
    
    public String getPattern() {
        return pattern;
    }

    public String getOriginalString() {
        return originalString;
    }

    public int getNrOfExamples() {
        return nrOfExamples;
    }

    public String getNote() {
        if (note == null) {
            return "";
        }
        else
            return note;
    }

    public String getRelation() {
        if (relation == null) {
            return "nothing assigned";
        }
        else
            return relation;
    }

    public String getMatchedRegex() {
        return matchedRegex;
    }
    public List getWords() {
        List<String> words = new ArrayList<String>();
        String[] toks = originalString.split(" ");
        int phraseIndex = 100;
        for(int i = 0; i < toks.length; i++) {
            if ((toks[i].startsWith("[")) || (toks[i].indexOf(']') >= 0)){
                phraseIndex = i;
            }
            else if (phraseIndex == i - 1) {
                words.add(toks[i]);
                phraseIndex = phraseIndex + 2;
            }
        }

        return words;
    }
    public List getPOStags() {
        List<String> posTags = new ArrayList<String>();
        String[] toks = pattern.split(" ");
        if(toks[0].equals("[COMMA]")) {
            posTags.add("COMMA");
            return posTags;
        }
        int ppIndex = 100;
        for(int i = 0; i < toks.length; i++) {

            if (toks[i].startsWith("[PP"))
                ppIndex = i;
            if (!(toks[i].startsWith("[")) || !(toks[i].indexOf(']') >= 0)) {
                if(!(ppIndex == i - 1)) {
                    posTags.add(toks[i]);
                }
            }
        }
        return posTags;
    }
    
    public int compareTo(Object patInf) {
        PatternInfo p = (PatternInfo)patInf;
        return pattern.compareTo(p.getPattern());
    }
    

}

