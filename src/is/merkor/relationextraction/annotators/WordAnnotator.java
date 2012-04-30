package is.merkor.relationextraction.annotators;

import java.util.StringTokenizer;

import is.merkor.relationextraction.types.Word;
import is.merkor.relationextraction.RegExMap;
import is.merkor.relationextraction.StringMapResource;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;


/**
 * Annotates "real" words in parsed texts (parsed with iceparser), i.e. no numbers, signs or tags,
 * according to a file including regular expressions representing non-words
 * 
 * @author Anna Nikulasdottir
 */

public class WordAnnotator extends JCasAnnotator_ImplBase {
  
	private String nonWordRegExFile = "nonWordRegEx";
	// A Map for different non-word regex to their expanded forms
	// Initialized from nonWordRegExFile
	private StringMapResource mMap; 
	private RegExMap mRegExMap;
  
  /**
   * @see AnalysisComponent#initialize(UimaContext)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    // get a reference to the String Map Resource
    try {
      mMap = (StringMapResource) getContext().getResourceObject(nonWordRegExFile);
      // Convert the regEx strings from mMap to Patterns
      mRegExMap = new RegExMap(mMap.getMap());
    } catch (ResourceAccessException e) {
      throw new ResourceInitializationException(e);
    }
  }

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
	
	System.out.println("processing WordAnnotator");  
    // go through document word-by-word
    String text = aJCas.getDocumentText();
    int pos = 0;
    
    StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r<.>/?\";:[{]}\\|=+()!", true);
    String token = "";
    while (tokenizer.hasMoreTokens()) {
      token = tokenizer.nextToken();
      // look up token in the regex map
      String word_string = mRegExMap.getRegExMatch(token);
      // if the token was not found, it is a valid word and a new Word annotation is created
      if (word_string.equals("")) {
        // create annotation
        Word annot = new Word(aJCas, pos, pos + token.length(), token.trim());
        annot.addToIndexes();
        
      } else if (word_string.equals("non_word") || word_string.equals("sign")) {
    	  if(!token.equals(" ")) {
    		  Word annot = new Word(aJCas, pos, pos + token.length(), word_string);
    		  annot.addToIndexes();
    	  }
      }
      // increment pos and go to next token
      pos += token.length();
    }
  }

}


