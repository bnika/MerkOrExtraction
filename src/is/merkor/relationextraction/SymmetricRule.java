package is.merkor.relationextraction;

public class SymmetricRule extends PatternRule {
	String relatedWords;
	
	public SymmetricRule(String wordClass) {
		this.relatedWords = wordClass;
	}
	
	public boolean isSymmetric() {
		return true;
	}
}

