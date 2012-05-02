package is.merkor.preprocessing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import is.merkor.relationextraction.RegExMap;
import is.merkor.relationextraction.types.PairWordPOS;
import is.merkor.util.FileCommunicatorReading;
import is.merkor.util.FileCommunicatorWriting;

public class IceTagsTreeTaggerMapping {
	private Map<String, String> tmpMap;
	private RegExMap ice2Tree;
	private List<String> lemmaList;
	private List<String> strudelLines;
	
	public IceTagsTreeTaggerMapping() {
		initializeMap();
		initializeLemmaList();
		strudelLines = new ArrayList<String>();
	}
	
	private void initializeMap() {
		tmpMap = new HashMap<String, String>();
		tmpMap.put("n\\Se\\Sg?", "NN");
		tmpMap.put("n\\Sf\\Sg?", "NNS");
		tmpMap.put("n\\Se\\S[g-]\\S", "NP");
		tmpMap.put("n\\Sf\\S[g-]\\S", "NPS");
		tmpMap.put("l\\S{4}f", "JJ");
		tmpMap.put("l\\S{4}m", "JJR");
		tmpMap.put("l\\S{4}e", "JJS");
		tmpMap.put("f\\S+", "PP");
		tmpMap.put("g\\S+", "DT");
		tmpMap.put("t\\S+", "CD");
		tmpMap.put("s[^þ]\\S+", "VV");
		tmpMap.put("sþ\\S+", "VVN");
		tmpMap.put("a[oþe]", "IN");
		tmpMap.put("aa", "RB");
		tmpMap.put("a[au][me]?", "RBR");
		tmpMap.put("a\\Se", "RBS");
		tmpMap.put("c\\S?", "CC");
		tmpMap.put("e", "FW");
		tmpMap.put("x", "FW");
		
		ice2Tree = new RegExMap(tmpMap);
	}
	private void initializeLemmaList() {
		lemmaList = FileCommunicatorReading.readListFromFile("resources/lex_items.txt");
	}
	public String getTreeTag(String icetag) {
		return ice2Tree.getRegExMatch(icetag);
	}
	public void collectStrudelFormatLines(PairWordPOS pair) {
		String wordform = pair.getWord().getWord_string().toLowerCase();
		String lemma = pair.getLemma();
		if(null == lemma)
			lemma = wordform;
		String treeTag;
		if(lemmaList.contains(lemma))
			treeTag = "NNCONCEPT";
		else
			treeTag = pair.getPos().getTreeTaggerTag();
		String strudelLine = wordform + " " + treeTag + "\t" + lemma;
		strudelLines.add(strudelLine);
	}
	public void writeStrudelFormat() {
		FileCommunicatorWriting.writeListAppend(strudelLines, "ordasjodur_strudel.txt");
//		try {
//			BufferedWriter out = FileCommunicator.createWriter("ordasjodur_strudel.txt", true);
//			
//			for(String s : strudelLines) {
//				out.write(s);
//				out.write("\n");
//			}
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}

