package is.merkor.patternextraction;

import is.merkor.util.database.DBCommunicator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class for the normalizing and merging of POS-patterns.
 * 
 * Compares each pattern with all other patterns in a list and 
 * returns a list with normalized and merged patterns.
 * 
 * Example:
 * Original patterns:
 *  [PP í  aþ [NP nxeþ ][NP nxee-ö ]] becomes 
 *  [PP Í  aþ [NP nxeþ ][NP nxeeg ]] becomes [pp í aþ [np nþ][np neg]]
 * 
 * Normalized patterns:
 * [pp í aþ [np nþ][np ne-s]]
 * [pp í aþ [np nþ][np neg]]
 * 
 * Merged pattern:
 * [pp í  aþ [np nþ ][np ne-s|neg ]] 
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class PatternMerger {
	
	LevenshteinDistance levDist;
	PatternGeneralizator patGen;
	
	public PatternMerger() {
		levDist = new LevenshteinDistance();
		patGen = new PatternGeneralizator();
	}
	
	private List<PatternInfo> getPatternsWithRelation (String relation, String password) {
		// set the name of your database!
		DBCommunicator comm = new DBCommunicator("patterns", password);
        return comm.getPatternsByRelation(relation);
	}
	
	// collect all pattern strings (to lower case) into a list
	private List<String> extractPatternStrings (List<PatternInfo> patternInfos) {
		List<String> patterns = new ArrayList<String>();
        for(PatternInfo p : patternInfos) {
            patterns.add(p.getPattern().toLowerCase());
        }
        return patterns;
	}
	
	/*
	 * Eliminate certain tags or replace by a general tag.
	 */
	private List<String> normalizePatterns (List<String> patterns) {
        Set<String> patternSet = new TreeSet<String>();
        for (String p : patterns) {
            p = p.replaceAll("nx(e|f)", "n");
            p = p.replaceAll("(-m|-s|-ö)", "-s");
            p = p.replaceAll("l[a-z]+", "l");
            p = p.replaceAll("f[a-qx]+", "f");
            patternSet.add(p);
        }
        List<String> normalizedPatterns = new ArrayList<String>(patternSet);
        return normalizedPatterns;
	}
	
	private List<String> mergePatterns (List<String> patterns) {
		
		Set<String> patternSet = new TreeSet<String>();
		int count = 0;
		while (true) {
            count++;
            Set<PatternDistance> distSet = comparePatterns(patterns);
            patterns = removeDistanceOne(distSet, patterns);
            List<String> generalPatts = generalizePatterns(distSet);
            // clear lists and add new general patterns to patternList
            patternSet.clear();
            patternSet.addAll(generalPatts);
            generalPatts.clear();
            patterns.addAll(patternSet);

            // if no new general patterns have been generated, end the process
            // count as additional condition to prevent an infinite loop
            if (patternSet.isEmpty() || count > 100)
                break;
        }
		return patterns;
	}
	
	private Set<PatternDistance> comparePatterns (List<String> patterns) {
		Set<PatternDistance> distSet = new HashSet<PatternDistance>();
		for(int i = 0; i < patterns.size(); i++) {
            String source = patterns.get(i);
            for(int j = i + 1; j < patterns.size(); j++) {
                String target = patterns.get(j);
                if (!ppWithSamePreposition(source, target))
                	continue;
                int dist = levDist.getLevenshteinDistanceWordBased(source, target, true);
                // for the moment, just collect pairs with dist == 1
                if(dist == 1) {
                	PatternDistance patDist = new PatternDistance(source, target, dist);
                    distSet.add(patDist);
                }
            }
        }
		return distSet;
	}
	private List<String> removeDistanceOne (Set<PatternDistance> set, List<String> patterns) {
		List<String> removeList = new ArrayList<String>();
        for (PatternDistance pats : set) {
            for(String p : patterns) {
                if (pats.getPattern_1().equals(p) || pats.getPattern_2().equals(p))
                    removeList.add(p);
            }
        }
        patterns.removeAll(removeList);
        return patterns;
	}
	private List<String> generalizePatterns(Set<PatternDistance> set) {
        List<String> generalPatts = new ArrayList<String>();
        for(PatternDistance pats : set) {
            generalPatts.add(patGen.generalizePattern(pats.getPattern_1(), pats.getPattern_2()));
        }
        return generalPatts;
	}
	/*
	 * Don't wan't to merge pp patterns that don't have the 
	 * same preposition
	 */
	private boolean ppWithSamePreposition (String source, String target) {
      if(source.startsWith("[pp")) {
          String sourceStart = source.substring(0,5);
          if(target.startsWith(sourceStart))
              return true;
          else
              return false;
      }
      return true;
    }
	
	/**
	 * Collects all patterns classified with {@code relation}, merges close patterns
	 * using Levenshtein distance and pattern generalization.
	 * Before the distance and generalization computation are perfomed, the 
	 * patterns are normalized.
	 * 
	 * Example:
	 * 
	 * [PP í  aþ [NP nxeþ ][NP nxee-ö ]] becomes [pp í aþ [np nþ][np ne-s]]
	 * [PP Í  aþ [NP nxeþ ][NP nxeeg ]] becomes [pp í aþ [np nþ][np neg]]
	 * 
	 * These normalized patterns have Levenshtein distance = 1 and their
	 * generalization / merging results in:
	 * 
	 * [pp í  aþ [np nþ ][np ne-s|neg ]]
	 * 
	 * @param relation
	 * @param outfile
	 * @return a list of normalized and merged patterns
	 */
	public List<String> process (String relation, String password) {
		
		List<PatternInfo> patternList = getPatternsWithRelation(relation, password);
		List<String> patterns = extractPatternStrings(patternList);
		patterns = normalizePatterns(patterns);
		List<String> result = mergePatterns(patterns);
		System.out.println("--- merged " + patternList.size() + " patterns for relation '" + relation + "'");
		System.out.println("--- nr. of merged patterns: " + result.size());
		return result;
	}
	
	/**
	 * Test method without db-call
	 * @param relation
	 * @param outfile
	 * @param patternList
	 * @return
	 */
	public List<String> process_test (String relation, List<PatternInfo> patternList) {
		List<String> patterns = extractPatternStrings(patternList);
		patterns = normalizePatterns(patterns);
		return mergePatterns(patterns);
	}
	
	private class PatternDistance {
		String pattern_1;
		String pattern_2;
		int distance;
		
		private PatternDistance (String pat1, String pat2, int dist) {
			pattern_1 = pat1;
			pattern_2 = pat2;
			distance = dist;
		}
		
		private String getPattern_1 () {
			return pattern_1;
		}
		private String getPattern_2 () {
			return pattern_2;
		}
		private int getDistance () {
			return distance;
		}
	}
}
