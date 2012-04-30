package is.merkor.util.database.datatypes;

import java.util.HashMap;
import java.util.Map;

public class LexicalRelationType {
	private int id;
	private String name;
	private String inversion;
	private String wordclasses;
	private boolean transitive;
	public static final HashMap<Integer, String> typeMap;
	
	//type map fitting to the table 'lex_relation_type' 02.02.2011

	static {
		typeMap = new HashMap<Integer, String>();
		
		typeMap.put(1, "hypernym");
		typeMap.put(2, "hyponym");
		typeMap.put(3, "propertyOf");
		typeMap.put(4, "hasProperty");
		typeMap.put(5, "attributeOf");
		typeMap.put(6, "hasAttribute");
		typeMap.put(7, "coordNouns");
		typeMap.put(8, "coordAdj");
		typeMap.put(9, "áAcc");
		typeMap.put(10, "áDat");
		typeMap.put(11, "að");
		typeMap.put(12, "af");
		typeMap.put(13, "án");
		typeMap.put(14, "andspænis");
		typeMap.put(15, "eftirAcc");
		typeMap.put(16, "eftirDat");
		typeMap.put(17, "frá");
		typeMap.put(18, "from_to_time");
		typeMap.put(19, "from_to");
		typeMap.put(20, "fyrirAcc");
		typeMap.put(21, "fyrirDat");
		typeMap.put(22, "gegn");
		typeMap.put(23, "gegnum");
		typeMap.put(24, "hjá");
		typeMap.put(25, "í_stað");
		typeMap.put(26, "íAcc");
		typeMap.put(27, "íDat");
		typeMap.put(28, "inn_í");
		typeMap.put(29, "innan");
		typeMap.put(30, "meðAcc");
		typeMap.put(31, "meðDat");
		typeMap.put(32, "meðfram");
		typeMap.put(33, "til");
		typeMap.put(34, "um");
		typeMap.put(35, "undirAcc");
		typeMap.put(36, "undirDat");
		typeMap.put(37, "úr");
		typeMap.put(38, "vegna");
		typeMap.put(39, "viðAcc");
		typeMap.put(40, "viðDat");
		typeMap.put(41, "yfirAcc");
		typeMap.put(42, "yfirDat");
		
	}
	
	public static Map getTypeMap () {
		return typeMap;
	}
	public static int getTypeId (String rel) {
		for (Integer i : typeMap.keySet()) {
			if (typeMap.get(i).equals(rel))
				return i;
		}
		return 0;
	}
	public LexicalRelationType(String name) {
		this.name = name;
		setIdForRelation(name);
	}
	public LexicalRelationType(int id, String name, String inversion, String wordclasses, boolean transitive) {
		this.id = id;
		this.name = name;
		this.inversion = inversion;
		this.wordclasses = wordclasses;
		this.transitive = transitive;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getInversion() {
		return inversion;
	}
	public String getWordclasses() {
		return wordclasses;
	}
	public boolean isTransitive() {
		return transitive;
	}
	public void setRelationForID(int id) {
		this.name = typeMap.get(id);
	}
	public void setIdForRelation(String relation) {
		
		for(Integer i : typeMap.keySet()) {
			if(typeMap.get(i).equals(relation))
				this.id = i;
		}
	}
	
	
}

