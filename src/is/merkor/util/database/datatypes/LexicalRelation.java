package is.merkor.util.database.datatypes;


/**
 * A class holding information about a lexical relation: the type of relation
 * and the lexical items involved
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class LexicalRelation implements Comparable<LexicalRelation> {
	private int id;
	private int relationType;	//an id of a LexicalRelationType
	private int fromLexUnit;	//an id of a LexicalUnit
	private int toLexUnit;		//an id of a LexicalUnit
	private String fromLemma;		//the lemma matching fromLexUnit
	private String toLemma;		//the lemma matching toLexUnit
	private String relation;
	private String inversion;
	private int frequency;
	private double score;
	private double logLike;
	
	public LexicalRelation(int id, int relationType, int fromLexUnit, int toLexUnit) {
		this.id = id;
		this.relationType = relationType;
		this.fromLexUnit = fromLexUnit;
		this.toLexUnit = toLexUnit;
		this.fromLemma = "";
		this.toLemma = "";
		this.relation = "";
		this.inversion = "";
	}
	public LexicalRelation(int id, int relationType, int fromLexUnit, int toLexUnit, double score, double logLike) {
		this(id, relationType, fromLexUnit, toLexUnit);
		this.score = score;
		this.logLike = logLike;
	}
	
	public int getId() {
		return id;
	}
	public int getRelationType() {
		return relationType;
	}
	public int getFromLexUnit() {
		return fromLexUnit;
	}
	public int getToLexUnit() {
		return toLexUnit;
	}
	public String getFromLemma() {
		return fromLemma;
	}
	public String getToLemma() {
		return toLemma;
	}
	public String getRelation() {
		return relation;
	}
	public String getInversion() {
		return inversion;
	}
	public void setFromLemma(String lemma) {
		this.fromLemma = lemma;
	}
	public void setToLemma(String lemma) {
		this.toLemma = lemma;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public void setInversion(String inversion) {
		this.inversion = inversion;
	}
	public void setFrequency(int f) {
		this.frequency = f;
	}
	public String createDBInsertStatement() {
		String statement = "INSERT INTO lex_relations_new (rel_type, from_lemma, from_bin_id, to_lemma, to_bin_id, frequency, score, log_likelihood) " +
				"VALUES (" + relationType + ", '" + fromLemma + "', " + fromLexUnit + ", '" + toLemma + "', " + toLexUnit + ", " + frequency + ", " + score + ", " +
				logLike + ");";
		
		return statement;
	}
	public boolean equals(LexicalRelation relation) {
		if(relationType == relation.getRelationType() && fromLexUnit == relation.getFromLexUnit() && toLexUnit == relation.getToLexUnit())
			return true;
		else
			return false;
	}
	/**
	 * The ordering of <code>LexicalRelation</code> objects should be:
	 * relation -> fromLemma -> toLemma
	 */
	@Override
	public int compareTo(LexicalRelation relation) {
		
		if(this.relation.compareTo(relation.getRelation()) == 0) {
			if(fromLemma.compareTo(relation.getFromLemma()) == 0)
				return toLemma.compareTo(relation.getToLemma());
			else
				return fromLemma.compareTo(relation.getFromLemma());
		}
		else
			return this.relation.compareTo(relation.getRelation());
	}
	public String toString() {
		return fromLemma + "-" + relation + "-" + toLemma;
	}
	
}
