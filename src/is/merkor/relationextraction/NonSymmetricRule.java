package is.merkor.relationextraction;

public class NonSymmetricRule extends PatternRule {
	private String basis;
	private String basisCasus;
	private String related;
	
	public NonSymmetricRule(String basis, String basisCasus, String related) {
		this.basis = basis;
		this.basisCasus = basisCasus;
		this.related = related;
	}
	public String getBasis() {
		return basis;
	}
	
	public String getBasisCasus() {
		return basisCasus;
	}
	
	public String getRelated() {
		return related;
	}
	
	public boolean isSymmetric() {
		return false;
	}
}

