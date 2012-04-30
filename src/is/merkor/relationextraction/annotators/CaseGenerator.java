package is.merkor.relationextraction.annotators;

import java.util.HashMap;

/**
 * Generates casus definitions for nouns and adjectives
 * from part-of-speech tags from IceParser
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class CaseGenerator {
	private HashMap<String, String> caseMappings;
	
	public CaseGenerator() {
		caseMappings = new HashMap<String, String>();
		caseMappings.put("n", "nominative");
		caseMappings.put("o", "accusative");
		caseMappings.put("þ", "dative");
		caseMappings.put("e", "genitive");
	}
	/**
	 * Returns the casus for nouns and adjectives according to <code>caseMappings</code>
	 * 
	 * @param wordClass	a String representing a word class (noun, verb, adj. etc.)
	 * @param token pos-tags from icetagger
	 * @return the correct case if the word class is a noun or an adjective, an empty String otherwise
	 */
	public String getCasus(String wordClass, String token) {
		if(wordClass.startsWith("noun") || wordClass.startsWith("adj")) {
	  	  char c = token.charAt(3);
	  	  String str = Character.toString(c);
	  	  return caseMappings.get(str);
	    }
		return "";
	}

}
