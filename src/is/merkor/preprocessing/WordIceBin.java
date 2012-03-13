package is.merkor.preprocessing;


/**
 * Data class containing a word, its ice-nlp tag and the corresponding bin tag.
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */
public class WordIceBin {
	
	private String word;
	private String iceTag;
	private String binTag;
	
	public WordIceBin (String word, String iceTag, String binTag) {
		this.word = word;
		this.iceTag = iceTag;
		this.binTag = binTag;
	}
	
	public String getWord() {
		return word;
	}
	public String getIceTag() {
		return iceTag;
	}
	public String getBinTag() {
		return binTag;
	}
	
	/**
	 * Returns true if and only if other is a wordIceBin and all instance variables are equal, false otherwise.
	 */
	@Override
	public boolean equals (Object other) {
		if (this == other) 
			return true;
		if (null == other)
			return false;
		
        if (getClass() != other.getClass()) 
        	return false;

        final WordIceBin word = (WordIceBin) other;

        return getWord().equals(word.getWord()) && getIceTag().equals(word.getIceTag()) && getBinTag().equals(word.getBinTag());
	}
	
	@Override
	public String toString() {
		return "word=" + getWord() + " iceTag=" + getIceTag() + " binTag=" + getBinTag();
	}

}
