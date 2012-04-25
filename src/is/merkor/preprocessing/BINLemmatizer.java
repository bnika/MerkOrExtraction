package is.merkor.preprocessing;

import is.merkor.util.FileCommunicatorWriting;
import is.merkor.util.IcenlpTags;
import is.merkor.util.MerkorCSVFile;
import is.merkor.util.ProcessingClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Lemmatizes a tagged text only with lemmata found in BIN (Beygingarlýsing Íslensks Nútímamáls).
 * Output contains a) lemmatized words with ids ('word_1234') and b) if not found in BIN, original strings without pos-tags
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */

public class BINLemmatizer extends ProcessingClass {
	private StringBuffer lemmatizedText;
	private List<String> nonLemmatized;
	private int lemmatized;
	private HashMap<String, String> wordformLemmaMap;
	//private String wordformLemmaFilename = "/Users/anna/MERKOR/Data/frequencies/wf_lemmaMap_sorted.csv";
	private  String wordformLemmaFilename = System.getProperty("user.dir") + "/resources/wordform_lemma.csv";
	private String outputfile = "lemmatized.txt";

	private String currentWord;
	private String currentTag;
	private char[] validWordclasses;
	
	private static int MAX_BUFFER_SIZE = 100000;
	
	/**
	 * TODO: add statistics: how many nouns /adjs /verbs lemmatized - how many not
	 */
	public BINLemmatizer() {
		lemmatizedText = new StringBuffer(MAX_BUFFER_SIZE);
		nonLemmatized = new ArrayList<String>();
		lemmatized = 0;
		initializeWfLemmaMap(wordformLemmaFilename);
		validWordclasses = new char[]{'n', 's', 'l'};  //nouns, verbs, adjectives
	}
	
	public void process(String token) {
		if (IcenlpTags.isTag(token)) {
			currentTag = token;
		}
		else {
			currentWord = token;
		}
		if (null != currentWord && null != currentTag) {
			if (isValidWordclass(currentTag)) { 
				String lemma = lemmatize(currentWord, currentTag);
				if (null != lemma) {
					appendWord(lemma);
					lemmatized++;
				}
				else {
					appendWord(currentWord);
					appendWord(currentTag);
					nonLemmatized.add(currentWord + "\t" + currentTag);
				}
			}
			else {
				appendWord(currentWord);
				appendWord(currentTag);
			}
			currentWord = null;
			currentTag = null;
		}
	}
	
	public String lemmatize (String word, String tag) {
		return wordformLemmaMap.get(word + '_' + tag);
	}
	
	private boolean isValidWordclass (String tag) {
		for (char c : validWordclasses) {
			if (tag.charAt(0) == c)
				return true;
		}
		return false;
	}
	private void appendWord(String word) {
		lemmatizedText.append(word + " ");
		if (lemmatizedText.length() >= MAX_BUFFER_SIZE) {
			writeText();
			lemmatizedText = new StringBuffer(MAX_BUFFER_SIZE);
		}
	}
	
	// wordformLemmaMap has the form 'wordform_nlptag => lemma_id'
	private void initializeWfLemmaMap(String filename) {
		System.out.println("initializing wordform_lemma map ...");
		wordformLemmaMap = new HashMap<String, String>();
		
		MerkorCSVFile file = new MerkorCSVFile(filename, ',');
		for (String[] line : file) {
			if (line.length >= 4) {
				//String wf = line[0] + "_" + line[1].replaceAll("\"", "");
				//String lemma = line[2] + "_" + line[3].replaceAll("\"", "");
				String wf = line[1] + "_" + line[2].replaceAll("\"", "");
				String lemma = line[3] + "_" + line[4].replaceAll("\"", "");
				wordformLemmaMap.put(wf, lemma);
			}
		}
	}
	private void writeText () {
		FileCommunicatorWriting.writeBufferToFileAppend(lemmatizedText, outputfile);
	}
	
	/**
	 * Append current lemmatized text to lemmatized output, write nonLemmatized list
	 * and print the numbers of lemmatized and non-lemmatized words (nouns, adjs, verbs) to stdout.
	 */
	public void finishProcess () {
		FileCommunicatorWriting.writeBufferToFileAppend(lemmatizedText, outputfile);
		FileCommunicatorWriting.writeListNonAppend("nonLemmatized.csv", nonLemmatized);
		System.out.println("\ntotal lemmatized: " + lemmatized);
		System.out.println("total non lemmatized (nouns, adjs, verbs): " + nonLemmatized.size());
	}
}