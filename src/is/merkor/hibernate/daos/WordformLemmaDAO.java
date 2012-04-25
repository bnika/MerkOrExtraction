package is.merkor.hibernate.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import is.merkor.hibernate.util.HibernateUtil;
import is.merkor.hibernate.data.WordformLemma;

public class WordformLemmaDAO extends HibernateGenericDAO<WordformLemma, Long> {

	public WordformLemmaDAO() {
		super(WordformLemma.class);
	}
	
	/**
	 * Find all items having param wordform and tag.
	 * 
	 * @throws {@link IllegalArgumentException} if wordform == null or tag == null
	 * @param wordform the wordform to search for
	 * @param tag the icenlp tag to search for
	 * @return a wordformLemma having param wordform as wordforma and tag as icenlp tag, or null if nothing is found.
	 */
	public WordformLemma findByWordformAndTag (String wordform, String tag) {
		if (null == wordform || null == tag) {
			throw new IllegalArgumentException ("parameter 'wordform' or parameter 'tag' must not be null!");
		}
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		try {
			Query query = session.getNamedQuery("wordformlemma.wordform.tag");
			query.setString(0, wordform);
			query.setString(1, tag);
			List<WordformLemma> result = query.list();
			if(!result.isEmpty())
				return result.get(0);
			else
				return null;
		} finally {
			session.close();
		}
	}
}
