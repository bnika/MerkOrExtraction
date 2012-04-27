package is.merkor.patternextraction;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Uses computed Levenshtein distance and the operation matrix from that computation to
 * build a generalized pattern from two input strings.
 * 
 * Example: source = "test string one"
 * 			target = "test string two"
 * 
 * result from PatternGeneralizator.generalizePattern(): "test string one|two"
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class PatternGeneralizator {
	
	private LevenshteinDistance levDist;
	
	public PatternGeneralizator() {
		levDist = new LevenshteinDistance();
	}
	
	public String generalizePattern (String source, String target) {
        levDist.getLevenshteinDistanceWordBased(source, target, true);
        String[] sourceArr = source.split(" "); 
        String[] targetArr = target.split(" ");
        String generalPattern = generalizePattern(sourceArr, targetArr);
        return generalPattern;
    }
	
    private String generalizePattern (String[] sourceArr, String[] targetArr) {
    	String generalPattern = "";
    	char[][] matrix = levDist.getOperationMatrix();
    	int i = sourceArr.length;
    	int j = targetArr.length;
    	// process the operation matrix (and source and target) backwards
        while (i > 0 && j > 0) {
        	char operation = matrix[i][j];
            if (operation == levDist.equal){
                generalPattern = sourceArr[i-1] + " " + generalPattern;
                i--;
                j--;
            }
            else if (operation == levDist.unequal) {
                generalPattern = sourceArr[i-1] + "|" + targetArr[j-1] + " " + generalPattern;
                generalPattern = removeDuplicates(generalPattern);
                i--;
                j--;
            }
            else if (operation == levDist.remove) {
                generalPattern = "* " + generalPattern;
                i--;
            }
            else if (operation == levDist.insert) {
                generalPattern = "* " + generalPattern;
                j--;
            }
        }
        return generalPattern;
    }
    
    // to prevent chainings like: ne|no|nn|no ...
    private String removeDuplicates (String pattern) {
    	String[] patArr = pattern.split(" ");
        for(int i = 0; i < patArr.length; i++) {
            if (patArr[i].contains("|")) {
                String[] orChain = patArr[i].split("\\|");
                List<String> chainList = Arrays.asList(orChain);
                TreeSet<String> chainSet = new TreeSet<String>(chainList);
                String newChain = "";
                for(String str : chainSet) {
                    newChain += "|" + str;
                }
                newChain = newChain.replaceFirst("\\|", "");
                patArr[i] = newChain;
            }
        }
        String resultPattern = "";
        for (int i = 0; i < patArr.length; i++) {
            resultPattern += patArr[i] + " ";
        }
        return resultPattern.trim();
    }
}
