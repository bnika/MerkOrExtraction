package is.merkor.relationextraction.annotators;

import java.util.Map;
import java.util.StringTokenizer;

import is.merkor.preprocessing.BINLemmatizer;
import is.merkor.relationextraction.types.POS;
import is.merkor.relationextraction.types.PairWordPOS;
import is.merkor.relationextraction.types.Word;
import is.merkor.preprocessing.IceTagsTreeTaggerMapping;
import is.merkor.relationextraction.RegExMap;
import is.merkor.relationextraction.StringMapResource;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
/**
 * Annotates the document text of a JCAS object with <code>Word</code> and <code>POS</code> (part-of-speech)
 * objects, and connects those to <code>PairWordPOS</code> objects. Lemmatizes the <code>Word</code> objects
 * according to <code>BINLemmatizer</code>.
 * <p>
 * Needs the files <code>resources/net/merkor/nonWordRegEx.txt</code> and
 * <code>resources/net/merkor/posTagMapping.txt</code>.
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class WordPOSAnnotator extends JCasAnnotator_ImplBase {
	private String nonWordRegExFile = "nonWordRegEx";
	private String posTagsFile = "posTagMapping";
	// A Map for different non-word regex to their expanded forms
	// Initialized from nonWordRegExFile
	private StringMapResource nonWordMap; 
	// A Map for pos-tags, initialized from posTagsFile
	private StringMapResource posMap; 	//contains a String-String map to read the posTagsFile into
	private RegExMap nonWordRegExMap;
	private RegExMap posRegExMap;		//a RegEx-String map to match against tokens
	private String wordClass;
	private CaseGenerator caseGenerator;
	private BINLemmatizer lemmatizer;
	
	private IceTagsTreeTaggerMapping treeTags;
	private boolean dot = false;
	 
	/**
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    caseGenerator = new CaseGenerator();
	    lemmatizer = new BINLemmatizer();
	    treeTags = new IceTagsTreeTaggerMapping();
	    // get a reference to the String Map Resources
	    try {
	      nonWordMap = (StringMapResource) getContext().getResourceObject(nonWordRegExFile);
	      // Convert the regEx strings from nonWordMap to Patterns
	      nonWordRegExMap = new RegExMap(nonWordMap.getMap());
	      posMap = (StringMapResource) getContext().getResourceObject(posTagsFile);
	      posRegExMap = new RegExMap(posMap.getMap());
	    } catch (ResourceAccessException e) {
	      throw new ResourceInitializationException(e);
	    }
	  }
	/**
	* @see JCasAnnotator_ImplBase#process(JCas)
	* 
	* Annotates the document text of the JCas with <code>Word, POS,</code> and <code>PairWordPOS</code>
	* 
	*/
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		System.out.println("processing WordPOSAnnotator ... "); 
		long startTime = System.currentTimeMillis();
	    String text = aJCas.getDocumentText();
	    int pos = 0;
	    StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r<.>/?\";:[{]}\\|=+()!", true); //took out '.' for strudel - put in again!
	    String token = "";
	    Word currentWord = null;
	 // go through document word-by-word
	    while (tokenizer.hasMoreTokens()) {
	      token = tokenizer.nextToken();
	      if(isWord(token)) {
	    	  //create Word Annotation
	    	  Word annot = new Word(aJCas, pos, pos + token.length(), token.trim());
		      annot.addToIndexes();
		      currentWord = annot;
		      // increment pos and go to next token
		      pos += token.length();
	      }
	      else if (isPOSTag(token)) {
	    	  //create POS Annotation
	    	  String casus = caseGenerator.getCasus(wordClass, token);
	    	  POS annot = new POS(aJCas, pos, pos + token.length(), wordClass, casus);
	    	  //add TreeTagger tag (use only for converting to TreeTagger format)
	    	  String treeTag = treeTags.getTreeTag(token);
	    	  annot.setTreeTaggerTag(treeTag);
	    	  
	    	  annot.addToIndexes();
	    	  //if currentWord is initialized, create a PairWordPOS annotation
	    	  if(null != currentWord && (annot.getBegin() - currentWord.getEnd()) <= 3) {
	    		  PairWordPOS pair = new PairWordPOS(aJCas, currentWord.getBegin(), annot.getEnd(), currentWord, annot);
				  //look for a lemma in BIN if the pair represents a noun, an adjective or a verb
			      //String lemma = lemmatizer.getLemmaForPair(pair);
	    		  String lemma = lemmatizer.lemmatize(pair.getWord().getCoveredText(), pair.getPos().getCoveredText());
				  if(!lemma.isEmpty()) {
				      pair.setLemma(lemma);
					  //lemmatizer.appendWord(lemma);
				  }
				 else {
				     // lemmatizer.appendWord(currentWord.getWord_string());
				 }
				  pair.addToIndexes();
				  currentWord = null;
				  treeTags.collectStrudelFormatLines(pair);
	    	  }
	    	  // increment pos and go to next token
	    	  currentWord = null;
		      pos += token.length();
		      
	      }
	      else if (token.equals(".")) {
	    	  if(!dot) {
	    		  Word annot = new Word(aJCas, pos, pos + token.length(), token.trim());
	    		  annot.addToIndexes();
	    		  currentWord = annot;
	    		  dot = true;
	    	  }
	    	  else if (null != currentWord && currentWord.getWord_string().equals(".")){  
	    		  POS posAnnot = new POS(aJCas, pos, pos + token.length(), "none", "none");
	    		  posAnnot.setTreeTaggerTag("SENT");
	    		  posAnnot.addToIndexes();
	    		  PairWordPOS pair = new PairWordPOS(aJCas,currentWord.getBegin(), posAnnot.getEnd(), currentWord, posAnnot); 
	    		  pair.setLemma(".");
	    		  pair.addToIndexes();
	    		  currentWord = null;
	    		  dot = false;
	    		  treeTags.collectStrudelFormatLines(pair);
	    	  }
	    	  pos += token.length();
	      }
	      else
	    	  pos += token.length();
	    }
	    //System.out.println("db-calls: " + lemmatizer.getDBCount());
	    //System.out.println("map-calls: " + lemmatizer.getMapCount());
	    
	    //Lemmatized text or strudel format??
	    //lemmatizer.writeLemmatizedText();
	    treeTags.writeStrudelFormat();
	    
	    long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
	private boolean isWord(String token) {
	    // if the token is not found in nonWordRegExMap, it is a valid word
	    if (nonWordRegExMap.getRegExMatch(token).isEmpty()) {
	    	return true;
	    } 
		return false;
	}
	private boolean isPOSTag(String token) {
		//if the token is found in posRegExMap, it is a pos-tag
		//the value of a pos tag in the map is a word class
		wordClass = posRegExMap.getRegExMatch(token);
		if(!wordClass.isEmpty())
			return true;
		return false;
	}

}
