package is.merkor.preprocessing;

import is.merkor.util.FileCommunicatorWriting;
import is.merkor.util.IcenlpTags;
import is.merkor.util.ProcessingClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class includes mappings between icenlp tags and bin tags (greiningarstrengur), 
 * as well as a set of rules for non valid words.
 * Creates a mapping between the icenlp tags of valid words and the corresponding bin tags
 * for nouns, verbs or adjectives.
 * Two kind of outputs are created: one for the valid words with mappings into a SQL-file
 * and one for non valid words into a .txt file.
 *  
 * @author Anna B. Nikulasdottir
 * @version 0.8
 */

public class IceTagsBinTagsMapping extends ProcessingClass {
		
	private final int MAX_LIST_SIZE = 100000; //max wordlist size before content is written out and list cleared
	
	private String currentWord;
	private String currentTag;
	
	private Map<String, String> binTagsMap;
	
	private List<WordIceBin> wordList = new ArrayList<WordIceBin>();
	private List<String> nonValidWords = new ArrayList<String>();
	private String wordclass;
		
	/**
	 * Constructor. Initializes a map with icetagger tags and the corresponding bin tags,
	 * and initializes a list with icetagger tags regular expressions.
	 */
	public IceTagsBinTagsMapping(String wordclass) {
		if(wordclass.matches("nouns?")) {
			this.wordclass = "nouns";
			this.binTagsMap = IceTagsBinTagsMaps.binTagsMap_nouns;
		}
		else if(wordclass.matches("verbs?")) {
			this.wordclass = "s_verbs";
			this.binTagsMap = IceTagsBinTagsMaps.binTagsMap_verbs;
		}
		else if(wordclass.matches("adjectives?")) {
			this.wordclass = "l_adjectives";
			this.binTagsMap = IceTagsBinTagsMaps.binTagsMap_adj;
		}
		else {
			System.out.println("Wordclass not valid! Please call the IceTagsBinTagsMapping constructor " +
					"with \"noun\", \"verb\" or \"adjective\"");
			System.exit(-1);
		}
	}
	
	public List<WordIceBin> getWordList() {
		return wordList;
	}
	public List<String> getNonWordList() {
		return nonValidWords;
	}
	
	/**
	 * Connects an icetagger tag with the corresponding bin tag ('greiningarstrengur').
	 * First checks if <code>token</code> is an icetagger tag, if yes, and if <code>word</code>
	 * is not <code>null</code>, writes the word, the icetagger tag and the corresponding
	 * bin tag into a SQL-file. If <code>token</code> is not an icetagger tag, initializes
	 * <code>word</code> with <code>token</code> and the next incoming token will
	 * be checked, if it is an icetagger tag.
	 * After writing a word-tag pair with the corresponding bin tag into file, <code>word</code>
	 * and <code>tag</code> are set to <code>null</code>
	 * 
	 * @param token either a word or an icetagger tag
	 */
	public void process(String token) {
		if (IcenlpTags.isTag(token))
			currentTag = token;
		else {
			currentWord = token;
			return;
		}
		if (null != currentWord) {
			if(isValidWordclass(currentTag)) { 
				if (isValid(currentWord)) {
					String binTag = getBinTag(currentTag);
					wordList.add(new WordIceBin(currentWord, currentTag, binTag));
				}
				else
					nonValidWords.add(currentWord);
			}
			currentWord = null;
			currentTag = null;
		}
		writeListsIfFull();
	}
		
	/**
	 * Writes the current content in wordlist and nonValidWord list and clears.
	 */
	public void finishProcess () {
		writeToSQLFile();
		writeNonValidWords();
		wordList.clear();
		nonValidWords.clear();
	}
	
	private void writeListsIfFull() {
		if (wordList.size() >= MAX_LIST_SIZE) {
			writeToSQLFile();
			wordList.clear();
		}
		if (nonValidWords.size() >= MAX_LIST_SIZE) {
			writeNonValidWords();
			nonValidWords.clear();
		}
	}
	// does the tag match the current wordclass?
	private boolean isValidWordclass(String tag) {
		return (tag.charAt(0) == wordclass.charAt(0));
	}
		
	public void writeToSQLFile () {
		System.out.println("\nwriting wordList to wordforms_" + wordclass + ".sql (" + wordList.size() + " items) ...");
		String sep = "', '";
		try {
			BufferedWriter out = FileCommunicatorWriting.createWriter("wordforms_" + wordclass + ".sql", true);
			for (WordIceBin word : wordList) {
				StringBuilder strBuild = new StringBuilder("INSERT INTO wordforms_");
				strBuild.append(wordclass);
				strBuild.append(" VALUES ('");
				strBuild.append(word.getWord());
				strBuild.append(sep);
				strBuild.append(word.getIceTag());
				strBuild.append(sep);
				strBuild.append(word.getBinTag());
				strBuild.append("');\n");
				out.write(strBuild.toString());
				//out.write("INSERT INTO wordforms_" + wordclass + " VALUES ('" + word.getWord() + "', '" + word.getIceTag() + "', '" + word.getBinTag() + "');\n");
				
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeNonValidWords () {
		System.out.println("writing non valid words to nonValidWords_" + wordclass + ".txt (" + nonValidWords.size() + " items) ...");
		
		try {
			BufferedWriter out = FileCommunicatorWriting.createWriter("nonValidWords_" + wordclass + ".txt", true);
			for (String word : nonValidWords) {
				out.write(word + "\n");
				
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private boolean isValid (String word) {
		  
		if(word.equals("á"))
			return true;
		if(word.length() <= 1)
			return false;
		if(word.length() > 40)
			return false;
		if(word.matches("\\S*[ÿäëñç]\\S*"))	//can not include foreign letters
			return false;
		if(word.matches("\\S*\\d+\\S*"))	//can not include digits
			return false;
		if(word.matches("\\S*-\\S+-\\S*"))	//can not include more than one '-'
			return false;
		if(word.matches("\\S*\\.\\S*"))		//can not include '.'
			return false;
		if(word.matches("aa-\\S{3,15}"))	//exception for double vowels: 'aa-fundur' and alike
			return true;
		if(word.matches("\\S+-"))			//can not end with an '-'
			return false;
		if(word.matches("\\S+-\\S{1,2}"))	//has to have more than two letters after an '-'
			return false;
		
		Pattern pat = Pattern.compile("\\S+[^aeiou]([aeiou])(\\1)(?!(\\1))\\S{3,}");	//two vowels are allowed if surrounded by enough other letters
		Matcher matcher = pat.matcher(word);
		if(matcher.matches()) {
			pat = Pattern.compile("((\\S)(\\2)\\S+)|(\\S+(\\S)(\\5)(\\5))");	//can not start or end with more than 2 or 3 identical letters
			matcher = pat.matcher(word);
			if(matcher.matches())
				return false;
			else
				return true;
		}
		pat = Pattern.compile("((\\S)(\\2)\\S+)|(\\S+(\\S)(\\5)(\\5))");	//can not start or end with more than 2 or 3 identical letters
		matcher = pat.matcher(word);
		if(matcher.matches())
			return false;
		pat = Pattern.compile("\\S*([aáeéiíoóuú])(\\1)\\S*"); //2 or more vowels in a row
		matcher = pat.matcher(word);
		if(matcher.find())
			return false;
		pat = Pattern.compile("\\S*(\\S)(\\1)(\\1)(\\1)\\S*");	//no more than 3 consonants in a row
		matcher = pat.matcher(word);
		if(matcher.find())
			return false;
			
		return true;
	}

	private String getBinTag(String pos) {
	    String binTag = "";
	    String iceTag = pos.replaceAll("[hkv]", "x");
	    if (wordclass.equals("nouns") || wordclass.equals("s_verbs")) {
		    binTag = binTagsMap.get(iceTag);
	    }
	    else if (wordclass.equals("l_adjectives")) {
		    //for adjectives - bin doesn't include 'indeclineable' (o):
		    char lastChar = pos.charAt(pos.length() - 1);
		    iceTag = pos.replaceAll("o([fme])", "s" + lastChar);
		    binTag = binTagsMap.get(iceTag);
	    }
	    if (null == binTag) {
		    binTag = "";
	    }
	    return binTag;
    }
}
