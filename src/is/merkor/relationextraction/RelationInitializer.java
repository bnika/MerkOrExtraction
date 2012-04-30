package is.merkor.relationextraction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;

import is.merkor.relationextraction.types.Lemma;
import is.merkor.relationextraction.types.PairWordPOS;
import is.merkor.relationextraction.types.SemRelation;
import is.merkor.util.FileCommunicatorWriting;
import is.merkor.util.database.DBQueryHandler;

/**
 * Contains methods to iterate through a <code>JCas</code> object annotated with not 
 * fully initialized <code>SemRelation</code> objects and finish the initialization and
 * prepare and write out database- and file-formats of the relations
 * @author Anna B. Nikulasdottir
 *
 */
public class RelationInitializer {
	//private FileCommunicatorWriting writer = new FileCommunicator();
	//private DBQueryHandler queryHandler = new DBQueryHandler("BIN");
	private List<String> queryList = new ArrayList<String>();
	
	public JCas setRelationWords(JCas aJCas) {
		FSIndex relationIndex = aJCas.getAnnotationIndex(SemRelation.type);
		Iterator relationIter = relationIndex.iterator();
		FSIndex lemmaIndex = aJCas.getAnnotationIndex(Lemma.type);
		Iterator lemmaIter = lemmaIndex.iterator();
		AnnotationIndex pairIndex = (AnnotationIndex)aJCas.getAnnotationIndex(PairWordPOS.type);
		//go through every SemRelation
		while (relationIter.hasNext()) {
			SemRelation relation = (SemRelation)relationIter.next();
			Iterator pairIterator = pairIndex.subiterator(relation);
			//initialize the SemRelation with lemmata - if present - or wordforms, and relation name
			extractRelationWords(relation, pairIterator, aJCas);
			//prepare and write output formats of the SemRelation
			if(!(null == relation.getWord1()) && !(null == relation.getWord2())) {
				FileCommunicatorWriting.writeRelation(relation.getWord1(), relation.getWord2(), relation.getRelation(), relation.getCoveredText());
				//queryList.add(queryHandler.insertRelation(relation.getWord1(), relation.getWord2(), relation.getRelation()));
			}
		}
		//the file insertRelationQueries.sql can be used to add to the database
		//instead of writing directly to the database from here, since this would be
		//to time consuming
		FileCommunicatorWriting.writeListAppend(queryList, "insertRelationQueries.sql");
		return null;
	}
	
	private void extractRelationWords(SemRelation relation, Iterator pairIterator, JCas aJCas) {
		
		String[] relationDescription = relation.getRelation().split("%");
		if(relationDescription.length < 3)
			System.out.println("relation description doesn't have the right format!");
		else {
			//XXX: still need to create that Relation class!
			String word1 = relationDescription[0];
			String word2 = relationDescription[2];
			String relationWord = relationDescription[1];
			int counter = 0;
			while(pairIterator.hasNext()) {
				counter++;
				PairWordPOS pair = (PairWordPOS)pairIterator.next();
				if(word1.equals(pair.getWord().getWord_string())) {
					if(null == pair.getLemma() || pair.getLemma().isEmpty())
						relation.setWord1(pair.getWord().getWord_string());
					else
						relation.setWord1(pair.getLemma());
					//if the relation includes a proper noun, the original
					//relation name is not used, proper noun relations are
					//not considered in the final results
					if(pair.getCoveredText().matches(".+[söm]g?"))
						relation.setRelation("proper");
					else 
						relation.setRelation(relationWord);
				}
				if(word2.equals(pair.getWord().getWord_string())) {
					if(null == pair.getLemma() || pair.getLemma().isEmpty())
						relation.setWord2(pair.getWord().getWord_string());
					else
						relation.setWord2(pair.getLemma());
					if(pair.getCoveredText().matches(".+[söm]g?"))
						relation.setRelation("proper");
				}
			}	
		}	
	}
}
