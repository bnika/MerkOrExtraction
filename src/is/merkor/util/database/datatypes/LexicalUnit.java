package is.merkor.util.database.datatypes;


public class LexicalUnit implements Comparable {
	private int id;
	private int bin_id;
	private String lemma;
	
	public LexicalUnit(int id, int bin_id, String lemma) {
		this.id = id;
		this.bin_id = bin_id;
		this.lemma = lemma;
	}
	
	public int getId() {
		return id;
	}
	public int getBinId() {
		return bin_id;
	}
	public String getLemma() {
		return lemma;
	}

	@Override
	public int compareTo(Object o) {
		LexicalUnit unit = (LexicalUnit)o;
		return this.lemma.compareTo(unit.getLemma());
	}

}
