package is.merkor.patternextraction;

/** 
 * Computes the edit distance between two strings.
 * Adapted from Ruiz-Casado et al. (2005): Automatic Extraction of Semantic Relationships for WordNet.
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class LevenshteinDistance {
	
	private int[][] distanceMatrix;
	private char[][] operationMatrix;
	
	// operations
	private final char remove = 'R';
	private final char insert = 'I';
	private final char equal = 'E';
	private final char unequal = 'U';
	
	/**
	 * Computes Levenshtein Distance of two strings based on chars.
	 * 
	 * @param source
	 * @param target
	 * @param ignoreCase
	 * @return
	 */
	public int getLevenshteinDistance (String source, String target, boolean ignoreCase) {
		if (ignoreCase) {
			source = source.toLowerCase();
			target = target.toLowerCase();
		}
		String[] sourceArr = initializeArray(source);
        String[] targetArr = initializeArray(target);
        
        if (sourceArr.length == 0)
            return targetArr.length;
 
        if (targetArr.length == 0)
            return sourceArr.length;
        
        initializeMatrices(sourceArr, targetArr);
        iterate(sourceArr, targetArr);

        // the last index is lev. dist.
        return distanceMatrix[sourceArr.length][targetArr.length];
	}
	/**
     * Compute Levenshtein Distance of two strings based on words instead of characters.
     * 
     * @param source source string
     * @param target target string
     * @return minimal edit distance
     */

    public int getLevenshteinDistanceWordBased (String source, String target, boolean ignoreCase) {
    	// move to calling method - if should not compute, don't call me!
       // if (!hasSamePreposition(s, t))
       //     return 100;
    	if (ignoreCase) {
    		source = source.toLowerCase();
    		target = target.toLowerCase();
    	}
    	String[] sourceArr = source.split(" "); 
        String[] targetArr = target.split(" ");
        
        if (sourceArr.length == 0)
            return targetArr.length;
 
        if (targetArr.length == 0)
            return sourceArr.length;
        
        initializeMatrices(sourceArr, targetArr);
        iterate(sourceArr, targetArr);

        // the last index is lev. dist.
        return distanceMatrix[sourceArr.length][targetArr.length];
    }
    
    private String[] initializeArray (String str) {
    	String[] resultArr = new String[str.length()];
    	for (int i = 0; i < resultArr.length; i++) {
        	resultArr[i] = String.valueOf(str.charAt(i));
        }
    	return resultArr;
    }
    private void initializeMatrices (String[] source, String[] target) {
    	int sourceDim = source.length;
    	int targetDim = target.length;
    	distanceMatrix = new int[sourceDim+1][targetDim+1];
        operationMatrix = new char[sourceDim+1][targetDim+1];
        
        // initialize first row/col with incrementing ints
        initFirstRowAndCol(sourceDim, targetDim);
    }
    
    private void initFirstRowAndCol (int rows, int cols) {
    	for (int i = 0; i <= rows; i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j <= cols; j++) {
            distanceMatrix[0][j] = j;
        }
    }
    
    private void iterate(String[] source, String[] target) {
    	for (int i = 1; i <= source.length; i++) {
            String sArr_i = source[i-1];
            // iterate through target and compare to source
            for (int j = 1; j <= target.length; j++) {
                String tArr_j = target[j-1];
                int cost = getCost(sArr_i, tArr_j);
                
                // get the minimum cost
                int above = distanceMatrix[i-1][j] + 1;
                int left = distanceMatrix[i][j-1] + 1;
                int above_left = distanceMatrix[i-1][j-1] + cost;

                distanceMatrix[i][j] = minimum(above, left, above_left);
                operationMatrix[i][j] = getOperation(distanceMatrix[i][j], above, left, above_left, cost);
            }
        }
    }
    
    private int getCost (String s_1, String s_2) {
    	if (s_1.equals(s_2))
    		return 0;
    	else
    		return 1;
    }
    
    private int minimum (int a, int b, int c) {
        int min = a;
        if (b < min)
            min = b;
        if (c < min)
            min = c;

        return min;
    }
    
    private char getOperation (int min, int a, int b, int c, int cost) {
    	if (min == a) {
            return remove;
        }
        else if (min == b) {
            return insert;
        }
        else if (min == c) {
            if (cost == 0)
                return equal;
            else
                return unequal;
        }
    	return 'X';
    }
}
