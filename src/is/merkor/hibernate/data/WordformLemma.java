package is.merkor.hibernate.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries ({
	@NamedQuery (name = "wordformlemma.wordform.tag",
		query = "select item from WordformLemma as item where wordform = ? and icetag = ?")
})

@Entity
@Table (name = "WORDFORM_LEMMA")
public class WordformLemma implements Serializable {
	
	Long id;
	String wordform;
	String icetag;
	String lemma;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="ID")
	public Long getId () {
		return id;
	}
	public void setId (Long id) {
		this.id = id;
	}
	@Column (name="WORDFORM")
	public String getWordform () {
		return wordform;
	}
	public void setWordform (String wordform) {
		this.wordform = wordform;
	}
	@Column (name="ICENLPTAG")
	public String getIcetag () {
		return icetag;
	}
	public void setIcetag (String tag) {
		this.icetag = tag;
	}
	@Column (name="LEMMA")
	public String getLemma () {
		return lemma;
	}
	public void setLemma (String lemma) {
		this.lemma = lemma;
	}
}
