package is.merkor.relationextraction;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RuleExtractor extracts rules from a Matcher object according to a rule String.
 * 
 * @author Anna B. Nikulásdóttir
 *
 */
public class RuleExtractor {
	
	/**
	 * Extracts rules from a Matcher object according to a rule String.
	 * The rule String is build according to groupings in the Matcher object, 
	 * so that the realisation of the rule will contain the respective groupings.
	 * 
	 * @param regExMatcher the Matcher to extract rules from
	 * @param rule the rule to map the content of the Matcher onto
	 * @return a List of concrete rule Strings
	 */
	public ArrayList<String> getRules(Matcher regExMatcher, String rule) {
		ArrayList<String> rules = new ArrayList<String>();
		String[] inputArr = rule.split("-");
		String relation = inputArr[0];
		
		//regEx at the end of the rule-input indicates a wildcard pattern in the main pattern
		//needs to be treated separately because of the non-dynamic of the matcher.group() methods.
		if(isRegEx(inputArr[inputArr.length-1])) {
			rules = extractIterativeRules(inputArr, regExMatcher, relation);
		}
		else
			rules = extractRules(inputArr, regExMatcher, relation);
		
		return rules;
	}
	
	private boolean isRegEx(String str) {
		return str.length() > 3;
	}
	//Extracts rules from patterns including wildcards
	private ArrayList<String> extractIterativeRules(String[] arr, Matcher regExMatcher, String relation) {
		ArrayList<String> rules = new ArrayList<String>();
		ArrayList<String> relatedWords = new ArrayList<String>();
		int basicWordGroup = 0;
		// Coordinated relations have no basic words, they are symmetric.
		// Other relations have one basic word to which the other words in the
		// rule are related by the relation described in the rule
		boolean basic = getBasic(arr);
		if(basic)
			basicWordGroup = Integer.decode(arr[1]);
		relatedWords = extractRelatedWords(arr, regExMatcher, basicWordGroup);
		// the wildcard pattern included in the Matcher
		Pattern starPat = Pattern.compile(arr[arr.length - 1]);
		// search for all matches of the wildcard pattern in the Matcher object
		Matcher starMatcher = starPat.matcher(regExMatcher.group());
		while(starMatcher.find()) {
			for(int i = 1; i <= starMatcher.groupCount(); i++) {
				if(!starMatcher.group(i).contains("[")) {
					if(!relatedWords.contains(starMatcher.group(i)))
						relatedWords.add(starMatcher.group(i));
				}
			}
		}
    	int nrOfBasicWords = getNrOfBasicWords(arr);
    	rules = makeRules(relatedWords, relation, basic, nrOfBasicWords);
		return rules;
	}
	
	//Extract simple rules without wildcards
	private ArrayList<String> extractRules(String[] arr, Matcher regExMatcher, String relation) {
		ArrayList<String> rules = new ArrayList<String>();
		ArrayList<String> relatedWords = new ArrayList<String>();
		int basicWordGroup = 0;
		boolean basic = getBasic(arr);
		if(basic)
			basicWordGroup = Integer.decode(arr[1]);
		relatedWords = extractRelatedWords(arr, regExMatcher, basicWordGroup);
		int nrOfBasicWords = getNrOfBasicWords(arr);
		rules = makeRules(relatedWords, relation, basic, nrOfBasicWords);
		return rules;
	}
	
	private boolean getBasic(String[] arr) {
		if(arr[0].startsWith("coord"))
			return false;
		else
			return true;
	}
	
	private int getNrOfBasicWords(String[] arr) {
		if(arr[arr.length-1].equals("!"))
			return 2;
		else
			return 1;
	}
	
	// Returns a list of related words extracted from groups of the regExMatcher.
	// If a basic word is included, it is the first word of the list.
	private ArrayList<String> extractRelatedWords(String[] arr, Matcher regExMatcher, int basicWordGroup) {
		ArrayList<String> words = new ArrayList<String>();
		String word = "";
		int groupCount = regExMatcher.groupCount();
		int ruleLength = getRuleLength(arr);
		int i = 1;
		if(basicWordGroup > 0) {
			if(basicWordGroup > groupCount)
				System.out.println(regExMatcher.group() + " does not have " + basicWordGroup + " groups!");
			else {
				//extract the word in group nr basicWordGroup as the basic word
				words.add(regExMatcher.group(basicWordGroup));
				//set i=2 because the first group in the matcher has already been extracted
				i = 2;
			}
		}
		//extract the words from the remaining groups of the matcher
		while(i < ruleLength) {
			if(basicWordGroup > groupCount)
				System.out.println(regExMatcher.group() + " does not have " + basicWordGroup + " groups!");
			else {
				word = regExMatcher.group(Integer.decode(arr[i].trim()));
				if(!words.contains(word))
					words.add(word);
			}
			i++;
		}
		return words;
	}
	private int getRuleLength(String[] arr) {
		//a regex at the end of arr is not included in the rule
		if(isRegEx(arr[arr.length-1]))
			return arr.length - 1;
		// a "!" means two basic words, is not a part of the rule
		if(arr[arr.length-1].equals("!"))
			return arr.length - 1;
		else
			return arr.length;
	}
	private ArrayList<String> makeRules(ArrayList<String> words, String relation, boolean basic, int nrOfBasicWords) {
		if(words.isEmpty()) {
			System.out.println("no content in words!");
			ArrayList<String> list = new ArrayList<String>();
			return list;
		}
		if(basic)
			return makeBasicWordRules(words, relation, nrOfBasicWords);
		else	
			return makeCoordinateRules(words, relation);
		
		
	}
	private ArrayList<String> makeCoordinateRules(ArrayList<String> words, String relation) {
		ArrayList<String> rules = new ArrayList<String>();
		String rule = "";
		for(String w : words) {
			for(int i = words.indexOf(w); i < words.size() - 1; i++) {
				rule = w + "%" + relation + "%" + words.get(i+1);
				rules.add(rule);
			}
		}
		return rules;
	}
	private ArrayList<String> makeBasicWordRules(ArrayList<String> words, String relation, int nrOfBasicWords) {
		ArrayList<String> rules = new ArrayList<String>();
		String basicWord1 = words.get(0);
		words.remove(0);
		String basicWord2 = "";
		if(2 == nrOfBasicWords) {
			basicWord2 = words.get(0);
			words.remove(0);
		}
		rules = formatRules(words, relation, basicWord1);
		if(!basicWord2.isEmpty())
			rules.addAll(formatRules(words, relation, basicWord2));
		
		return rules;
			
	}
	private ArrayList<String> formatRules(ArrayList<String> words, String relation, String basicWord) {
		ArrayList<String> rules = new ArrayList<String>();
		String rule = "";
		if(relation.startsWith("attr") || relation.startsWith("hyp") || relation.startsWith("property")) {
			for(String w : words) {
				if(!w.equals(basicWord)) {
					rule = w + "%" + relation + "%" + basicWord;
					rules.add(rule);
					
				}
			}
		}
		else {
			for(String w : words) {
				if(!w.equals(basicWord)) {
					rule = basicWord + "%" + relation + "%" + w;
					rules.add(rule);
					
				}
			}
		}
	
	return rules;
	}

}

