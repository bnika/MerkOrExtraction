package is.merkor.patternextraction;

//import patternstatistics.DBSelection;
import is.merkor.util.database.DBCommunicator;
import java.util.*;
import java.io.*;

/**
 *
 * @author anna
 */
public class LevenshteinDistanceOriginal {

    /**
     * Save Levenshtein Distance:
     * pattern1, pattern2, distance
     */

    private String pattern1;
    private String pattern2;
    private int distance;

    // information from ld calculation, needed for generalizing patterns
    private String[] sArr; //source array
    private String[] tArr; //target array
    private char[][] op; //operation matrix
    private int n; // matrix dimension n
    private int m; // matrix dimension m

    public LevenshteinDistanceOriginal() {

    }

    public LevenshteinDistanceOriginal(String p1, String p2, int dist) {
        pattern1 = p1;
        pattern2 = p2;
        distance = dist;
    }

    public int getDistance() {
        return distance;
    }
    public String getPattern1() {
        return pattern1;
    }
    public String getPattern2() {
        return pattern2;
    }
    
    /**
     * Compute Levenshtein Distance
     * 
     * @param s source string
     * @param t target string
     * @return minimal edit distance
     */

    //public int getLevenshteinDistance (String s, String t) {
    public int getLevenshteinDistance (String s, String t) {

        if (!hasSamePreposition(s, t))
            return 100;
        
        int d[][]; // matrix
        //char op[][]; // matrix with operation symbols
        //int n; // length of s
        //int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        String sArr_i; // ith String of sArr
        String tArr_j; // jth String of tArr
        int cost = 0;

        s = s.toLowerCase();
        t = t.toLowerCase();

        sArr = s.split(" ");
        tArr = t.split(" ");
        // Step 1, check strings for "" and create the matrix

        //n = s.length();
        //m = t.length();

        n = sArr.length;
        m = tArr.length;
        // source is empty, ld is length of target
        if (n == 0)
            return m;
            //return "";
        // target is empty, ld is length of source
        if (m == 0)
            return n;
            //return "";
        // source and target are not empty, create the matrix
        d = new int[n+1][m+1];
        op = new char[n+1][m+1];

        // Step 2, initialize first row/col with incrementing ints

        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        
        // Step 3, iterate through source

        for (i = 1; i <= n; i++) {
            //s_i = s.charAt(i-1);
            sArr_i = sArr[i-1];
            // Step 4, iterate through target and compare to source-char
            for (j = 1; j <= m; j++) {
                //t_j = t.charAt(j-1);
                tArr_j = tArr[j-1];

                //if (s_i == t_j)
                if(sArr_i.equals(tArr_j))
                    cost = 0;
                else
                    cost = 1;

                // Step 5, get the minimum
                int a = d[i-1][j] + 1;
                int b = d[i][j-1] + 1;
                int c = d[i-1][j-1] + cost;

                //d[i][j] = minimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);
                int min = minimum(a, b, c);
                d[i][j] = min;

                // fill in the operation matrix (R=remove, I=insert, E=equal, U=unequal)
                if (min == a) {
                    op[i][j] = 'R';
                }
                else if (min == b) {
                    op[i][j] = 'I';
                }
                else if (min == c) {
                    if (cost == 0)
                        op[i][j] = 'E';
                    else
                        op[i][j] = 'U';
                }
            }

            
        }
//        if(d[n][m] < 2){
//            String generalPat = generalizePattern(sArr, tArr, op, n, m);
//            //System.out.println(s + " " + t + "generalPattern: " + generalPat);
//            return generalPat;
//        }
//        else
//            return "";
        // Step 6, return the last index, which is lev. dist.
        return d[n][m];
    }

    /**
     * Get the minimum of three values
     */

    private int minimum(int a, int b, int c) {


        int min;

        min = a;
        if (b < min)
            min = b;
        if (c < min)
            min = c;

        return min;

    }

    private boolean hasSamePreposition(String source, String target) {
//        String s = source.toLowerCase();
//        String t = target.toLowerCase();
        if(source.startsWith("[pp")) {
            String sourceStart = source.substring(0,5);
            if(target.startsWith(sourceStart))
                return true;
            else
                return false;
        }
        return true;
    }
    public String generalizePattern(String s, String t) {
        String generalPattern = "";
        getLevenshteinDistance(s, t);
        generalPattern = generalizePattern(sArr, tArr, op, n, m);
          return generalPattern;
    }
    public String generalizePattern(String[] s, String[] t, char[][] matrix, int n, int m) {
        String generalPattern = "";
        int i = n;
        int j = m;
        while(i > 0 && j > 0) {
            
            if(matrix[i][j] == 'E'){
                generalPattern = s[i-1] + " " + generalPattern;
                i--;
                j--;
            }
            if(matrix[i][j] == 'U') {

                generalPattern = s[i-1] + "|" + t[j-1] + " " + generalPattern;
                // to prevent chainings like: ne|no|nn|no ...
                String[] patArr = generalPattern.split(" ");
                for(int k = 0; k < patArr.length; k++) {
                    if (patArr[k].contains("|")) {
                        String[] orChain = patArr[k].split("\\|");
                        List<String> chainList = Arrays.asList(orChain);
                        TreeSet<String> chainSet = new TreeSet<String>();
                        chainSet.addAll(chainList);
                        String newChain = "";
                        for(String str : chainSet) {
                            newChain += "|" + str;
                        }
                        newChain = newChain.replaceFirst("\\|", "");
                        patArr[k] = newChain;
                        
                    }
                }
                generalPattern = "";
                for (int l = 0; l < patArr.length; l++) {
                    generalPattern += patArr[l] + " ";
                }
                generalPattern.trim();
                //generalPattern = b + "|" + e + " " + generalPattern;
                i--;
                j--;
            }
            if(matrix[i][j] == 'R') {
                generalPattern = "* " + generalPattern;
                i--;
            }
            if(matrix[i][j] == 'I') {
                generalPattern = "* " + generalPattern;
                j--;
            }
        }
        return generalPattern;
    }

    public void writePatterns(List<String> set, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF8"));

            for(String s : set) {
                writer.write(s);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LevenshteinDistanceOriginal ld = new LevenshteinDistanceOriginal();
        int dist;
        DBCommunicator comm = new DBCommunicator();
        List<PatternInfo> patternList = new ArrayList<PatternInfo>();
        String allPatternsFilename = "allPatternsGenitive.csv";
        String generalPatternsFilename = "generalPatternsGenitive.csv";
        String relation = "genitive";

        // get all patterns marked with the certain relation
        patternList = comm.getPatternsByRelation(relation);//("role");//("genitive");

        // extract only patterns from patternList:
        List<String> patterns = new ArrayList<String>();
        for(PatternInfo p : patternList) {
            patterns.add(p.getPattern().toLowerCase());
        }
        
        System.out.println("size of patternList from DB: " + patternList.size());
        

        // normalize patterns:
        TreeSet<String> patternSet = new TreeSet<String>();
        for (String p : patterns) {
            p = p.replaceAll("nx(e|f)", "n");
            p = p.replaceAll("(-m|-s|-ö)", "-s");
            p = p.replaceAll("l[a-z]+", "l");
            p = p.replaceAll("f[a-qx]+", "f");
            patternSet.add(p);
        }
        patterns.clear();
        patterns.addAll(patternSet);

        System.out.println("size of patternList after normalizing: " + patterns.size());

        // write all normalized patterns to file
        ld.writePatterns(patterns, allPatternsFilename);
        String pat1;
        String pat2;
        String target = "";
        int threshold = 0;
        int count = 0;
        while(threshold < 2) {
            count++;
            List<LevenshteinDistanceOriginal> distList = new ArrayList<LevenshteinDistanceOriginal>();
            for(int i = 0; i < patterns.size(); i++) {
                String source = patterns.get(i);

                for(int j = i + 1; j < patterns.size(); j++) {
                    target = patterns.get(j);
                    // get minimum edit distance for source and target
                    dist = ld.getLevenshteinDistance(source, target);
                    LevenshteinDistanceOriginal distObj = new LevenshteinDistanceOriginal(source, target, dist);
                    // for the moment, just collect pairs with dist == 1
                    if(dist == 1) {
                        distList.add(distObj);
                        //System.out.println("ld of " + source + " and " + target + " is: " + dist);
                    }
                }
            }// end of edit distance calculation

            // nr. of pattern-pairs with dist == 1
            System.out.println("Iteration nr. " + count);
            System.out.println("Size of distList: " + distList.size());
      

            // if unknown minimum ist to be found, use this
//            int min = 0;
//            int curr;
//            for(LevenshteinDistance obj : distList) {
//                curr = obj.getDistance();
//                if (min == 0 || curr <= min) {
//                    min = curr;
//                }
//            }

            List<LevenshteinDistanceOriginal> minList = new ArrayList<LevenshteinDistanceOriginal>();
            List<String> removeList = new ArrayList<String>();
            for(LevenshteinDistanceOriginal obj : distList) {
                if(obj.getDistance() == 1) {
                    minList.add(obj); 
                }
            }
            // find the patterns with dist == 1 and remove them from patterns
            for (LevenshteinDistanceOriginal obj : minList) {
                pat1 = obj.getPattern1();
                pat2 = obj.getPattern2();

                for(String p : patterns) {
                    if (pat1.equals(p) || pat2.equals(p))
                        removeList.add(p);
                }
            }
            patterns.removeAll(removeList);

//            System.out.println("Size of minList: " + minList.size());

            System.out.println("Size of patternList after removing \"removeList\": " + patterns.size());

            // generate general patterns for all pattern-pairs in minList
            List<String> generalPatts = new ArrayList<String>();
            for(LevenshteinDistanceOriginal obj : minList) {
                generalPatts.add(ld.generalizePattern(obj.getPattern1(), obj.getPattern2()));
            }
            // clear lists and add new general patterns to patternList, use patternSet to avoid doubling of patterns
            minList.clear();
            patternSet.clear();
            patternSet.addAll(generalPatts);
            generalPatts.clear();
            patterns.addAll(patternSet);

            System.out.println("Size of new patternList: " + patterns.size());
            System.out.println("\n");

            // if no new general patterns have been generated, end the process
            if (patternSet.isEmpty())
                threshold = 2;
            else {
                threshold = 1;
                ld.writePatterns(patterns, generalPatternsFilename);
            }
        }


        // final set of general patterns ready
        System.out.println("Total size of general patterns: " + patterns.size());
        int c = 0;
        for (String s : patterns) {
            c++;
            System.out.println("pattern: " + c + " " + s);
        }
        System.out.println("Total size of genral patterns: " + patterns.size());

    }

}
