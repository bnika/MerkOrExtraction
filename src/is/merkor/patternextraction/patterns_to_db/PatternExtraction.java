package is.merkor.patternextraction.patterns_to_db;

/**
 * Just for documentation purpose - needs extreme rework!!
 * @author anna
 */

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatternExtraction {
    private String validStartRegex = "((\\[NPs?.+)|(\\[PP.+)|(\\[AP.+))";
    private String conjunctionsRegex = "(\\[CP.+|,.*)";
    //private String delimiter = "####";
    private List<PatternInfo> patternSequence = new ArrayList<PatternInfo>();
    private OutputStreamWriter patternsOut;
    private DBInsertion dbInsertion; 
    
    
    public PatternExtraction() {
        try {
            patternsOut = new OutputStreamWriter(new FileOutputStream("RaduneytiPatternsOut"), "UTF8");
        } catch (IOException ioe) {
            System.out.println("couldn't create file");
        }
        try {
            dbInsertion = new DBInsertion();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
    
    public void closeOutStream() {
        try {
            patternsOut.close();
        } catch (IOException ioe) {
            System.out.println("couldn't close out file");
        }
    }
    
    public void processLine(String line) {
        if(hasValidStart(line)) {
            PatternInfo pat = extractPatternFrom(line);
            patternSequence.add(pat);
        }
        else if(isConjunction(line) && (!patternSequence.isEmpty())) {
            PatternInfo pat = new PatternInfo(line, line);
            patternSequence.add(pat);
        }
        else {
            testPatternSequence();
            if (!patternSequence.isEmpty())
                writePatternSequence();
            patternSequence.clear();
        }
    }
    private void testPatternSequence() {
        if(patternSequence.isEmpty())
            return;
        if(patternSequence.size() > 1) {
            int lastIndex = patternSequence.size() - 1;
            String lastElem = patternSequence.get(lastIndex).getPattern();
            if (isNonValidNP(lastElem)) {
                patternSequence.remove(lastIndex);
                lastIndex = patternSequence.size() - 1;
                lastElem = patternSequence.get(lastIndex).getPattern();
            }
            lastIndex = patternSequence.size() - 1;
            lastElem = patternSequence.get(lastIndex).getPattern();
            while (isConjunction(lastElem)) {
                patternSequence.remove(lastIndex);
                lastIndex = patternSequence.size() - 1;
                lastElem = patternSequence.get(lastIndex).getPattern();
            }

            lastIndex = patternSequence.size() - 1;
            lastElem = patternSequence.get(lastIndex).getPattern();
            while (isSimplePP(lastElem)) {
                patternSequence.remove(lastIndex);
                lastIndex = patternSequence.size() - 1;
                lastElem = patternSequence.get(lastIndex).getPattern();
            }
        }
        if(patternSequence.size() == 1) {
            PatternInfo pat = patternSequence.get(0);
            String [] toks = pat.getPattern().split(" ");
            if(toks.length <= 4) {
                patternSequence.clear();
                return;
            }
            if(toks[0].equals("[PP")) {
                if(toks.length <= 7) {
                    patternSequence.clear();
                    return;
                }
            }
        }

        if(!hasValidPosTags()){
            patternSequence.clear();
            return;
        }
        if (!hasValidWords()) {
            patternSequence.clear();
            return;
        }
    }

    public boolean hasValidPosTags() {
        List<String> tags = new ArrayList<String>();
        // patternSequence has to include at least:
        // 2x n || 2x l || (1x n & 1x l)
        int nounCount = 0;
        int adjCount = 0;
        for(PatternInfo p : patternSequence) {
            tags = p.getPOStags();
            for(String s : tags) {
                if(s.startsWith("n"))
                    nounCount++;
                if(s.startsWith("l"))
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
        return line.matches(conjunctionsRegex);
    }

    private boolean isSimplePP(String line) {
        if(line.startsWith("[PP")) {
            String[] toks = line.split(" ");
            if(toks.length < 5)
                return true;
        }
        return false;
    }
    private boolean isNonValidNP(String line) {
        if(line.startsWith("[NP")){
            String[] toks = line.split(" ");
            if((toks.length < 5) && toks.length > 2) {
                if(!(toks[2].startsWith("n")))
                    return true;
            }
        }
        return false;
    }
    /**
     * Extracts pattern from a parsed input line
     * Example:
     * "[PP í aþ [NP [AP fyrsta lkeþve AP] leikhluta nkeþ NP] PP]"
     * becomes:
     * "[PP í  aþ [NP[AP lkeþve ] nkeþ ]]"
     *
     * @param line input line from parsed file
     * @return PatternInfo including extracted pattern and <c>line</c>
     */
    private PatternInfo extractPatternFrom(String line) {
        String [] tokens = line.split(" ");
        String pattern = "";
        Boolean openFlag = false;
        Boolean pp_cpFlag = false;
        
        
        for (int i = 0; i < tokens.length; i++) {
            //check if start of a phrase
            if(tokens[i].startsWith("[")) {
                pattern = pattern.concat(tokens[i]);
                openFlag = true; //indicates an unclosed phrase
                //if PP or CP, the concrete word belongs to the pattern
                if (tokens[i].startsWith("[PP") || tokens[i].startsWith("[CP"))
                    pp_cpFlag = true;
            }
            //check if end of a phrase, don't write the phrase name again, just close with ]
            else if (tokens[i].endsWith("]")) {
                pattern = pattern.concat("]");
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
    
    private void writePatternSequence() {
        
        try {

            dbInsertion.insertToDB(patternSequence);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatternExtraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatternExtraction.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(patternSequence.toString());
        }
        for (int i = 0; i < patternSequence.size(); i++) {
            PatternInfo pat = patternSequence.get(i);
            try {
                patternsOut.write(pat.getPattern());
                patternsOut.write("\t");
                patternsOut.write(pat.getOriginalString());
                patternsOut.write("\n");
                
            } catch (IOException ioe) {
                System.out.println("couldn't write to file");
            }
        }
        try {
            patternsOut.write("\n");
            patternsOut.write("###########################");
            patternsOut.write("\n\n");
        } catch (IOException ioe) {
            System.out.println("couldn't write to file");
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String dirName = "raduneyti_parsed";
        
        String[] dir = new File(dirName).list();
        
        for (int i = 1; i < dir.length; i++) {
            String inFile = dir[i];
            //String inFile = "lemma_E0J_forParser.txt.out";
            System.out.println("analysing: " + inFile + " ...");
            String[] inFileParts = inFile.split("\\.");
            String outFile = "petTest/" + inFileParts[0] + "_patterns." + inFileParts[1];
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(dirName + "/" + inFile), "UTF8"));
                String line = "";
                PatternExtraction extr = new PatternExtraction();
                //boolean emptyFlag = true;
                int cnt = 0;
//                extr.dbInsertion.beginTransaction();
                while ((line = input.readLine()) != null)
                {
                    //static int cnt=0;

//                    if ((cnt % 5000) == 0) {
//                        extr.dbInsertion.endTransaction();
//                        extr.dbInsertion.beginTransaction();
//                    }
                    extr.processLine(line);
                    cnt++;

                }
//                extr.dbInsertion.endTransaction();
                input.close();
                try {
                    //extr.closeOutStream();
                    extr.dbInsertion.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(PatternExtraction.class.getName()).log(Level.SEVERE, null, ex);
                }



            } catch (IOException ioe) {
                System.out.println("file not found");
            }
//            catch (SQLException ex) {
//               Logger.getLogger(PatternExtraction.class.getName()).log(Level.SEVERE, null, ex);
//            }
       } //end of for
    }

}
