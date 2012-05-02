package is.merkor.relationextraction.annotators;

import java.util.Iterator;

import is.merkor.preprocessing.BINLemmatizer;
import is.merkor.relationextraction.types.Word;
import is.merkor.relationextraction.types.POS;
import is.merkor.relationextraction.types.PairWordPOS;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;

public class PairWordPOSAnnotator extends JCasAnnotator_ImplBase {
	private JCas jCas; 
	private int pairBegin = 0;
	private BINLemmatizer lemmatizer;
	
	public PairWordPOSAnnotator() {
		lemmatizer = new BINLemmatizer();
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		System.out.println("processing PairWordPOSAnnotator");
		long startTime = System.currentTimeMillis();
		jCas = aJCas;
		// get indices of Words and POS from aJCas
		FSIndex wordIndex = aJCas.getAnnotationIndex(Word.type);
		FSIndex posIndex = aJCas.getAnnotationIndex(POS.type);
		
		Iterator wordIter = wordIndex.iterator();
		//iterate through all Word objects
		while (wordIter.hasNext()) {
			Word word = (Word)wordIter.next();
			int wordEnd = word.getEnd();
			FSIterator posIter = posIndex.iterator();
			//create a help POS object to position the POS-iterator
			POS positionPOS = new POS(aJCas, word.getBegin(), word.getEnd() + 6);
			posIter.moveTo(positionPOS);
			//iterate through POS objects to find the next POS to the current Word object
			while (posIter.hasNext()) {
				POS pos = (POS)posIter.next();
				if(pos.getBegin() == wordEnd + 1) {
					int begin = word.getBegin();
					if(begin != pairBegin) {
						pairBegin = begin;
						//create new PairWordPOS objcet with current Word and current POS
						PairWordPOS pair = new PairWordPOS(jCas, word.getBegin(), pos.getEnd(), word, pos);
						//look for a lemma in BIN if it the pair represents a noun, an adjective or a verb
						//String lemma = lemmatizer.getLemmaForPair(pair);
						String lemma = lemmatizer.lemmatize(word.getWord_string(), pos.getCoveredText());
						if(!lemma.isEmpty()) {
							pair.setLemma(lemma);
							//lemmatizer.appendWord(lemma);
						}
						else
							//lemmatizer.appendWord(word.getWord_string());
						pair.addToIndexes();
					}
					//right POS object found
					break;
				}
			}
		}
	//	lemmatizer.writeLemmatizedText();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}
