package is.merkor.preprocessing;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps for ice-nlp tags to BÍN-tags
 * @author Anna B. Nikulásdóttir
 * @version 0.8
 *
 */
public class IceTagsBinTagsMaps {
	
	public static Map<String, String> binTagsMap_nouns;
	
	static {
		// before using this map, be sure to replace "[hkv]" with "x" in 
		// the ice-tag 
		binTagsMap_nouns = new HashMap<String, String>();
		binTagsMap_nouns.put("nxen", "NFET");
	    binTagsMap_nouns.put("nxeng", "NFETgr");
	    binTagsMap_nouns.put("nxeo", "ÞFET");
	    binTagsMap_nouns.put("nxeog", "ÞFETgr");
	    binTagsMap_nouns.put("nxeþ", "ÞGFET");
	    binTagsMap_nouns.put("nxeþg", "ÞGFETgr");
	    binTagsMap_nouns.put("nxee", "EFET");
	    binTagsMap_nouns.put("nxeeg", "EFETgr");
	    binTagsMap_nouns.put("nxfn", "NFFT");
	    binTagsMap_nouns.put("nxfng", "NFFTgr");
	    binTagsMap_nouns.put("nxfo", "ÞFFT");
	    binTagsMap_nouns.put("nxfog", "ÞFFTgr");
	    binTagsMap_nouns.put("nxfþ", "ÞGFFT");
	    binTagsMap_nouns.put("nxfþg", "ÞGFFTgr");
	    binTagsMap_nouns.put("nxfe", "EFFT");
	    binTagsMap_nouns.put("nxfeg", "EFFTgr");
	}
	
	public static Map<String, String> binTagsMap_verbs;
	
	static {
		binTagsMap_verbs = new HashMap<String, String>();
		binTagsMap_verbs.put("sfg1en", "GM-FH-NT-1P-ET");	// 'er'
		binTagsMap_verbs.put("svg1en", "GM-VH-NT-1P-ET");	// 'sé'
		binTagsMap_verbs.put("sfm1en", "MM-FH-NT-1P-ET");	// 'hefst'
		binTagsMap_verbs.put("svm1en", "MM-VH-NT-1P-ET");	// 'hafist'
		binTagsMap_verbs.put("sbg2en", "GM-BH-ET");			// 'vertu'
		binTagsMap_verbs.put("sfg2en", "GM-FH-NT-2P-ET");	// 'ert'
		binTagsMap_verbs.put("svg2en", "GM-VH-NT-2P-ET");	// 'sért'
		binTagsMap_verbs.put("sbm2en", "MM-BH-ET");			// 'aðhafstu'
		binTagsMap_verbs.put("sfm2en", "MM-FH-NT-2P-ET");	// 'hefst'
		binTagsMap_verbs.put("svm2en", "MM-VH-NT-2P-ET");	// 'hafist'
		binTagsMap_verbs.put("sfg3en", "GM-FH-NT-3P-ET");	// 'er'
		binTagsMap_verbs.put("svg3en", "GM-VH-NT-3P-ET");	// 'sé'
		binTagsMap_verbs.put("sfm3en", "MM-FH-NT-3P-ET");	// 'hefst'
		binTagsMap_verbs.put("svm3en", "MM-VH-NT-3P-ET");	// 'hafist'
		binTagsMap_verbs.put("sfg1fn", "GM-FH-NT-1P-FT");	// 'erum'
		binTagsMap_verbs.put("svg1fn", "GM-VH-NT-1P-FT");	// 'séum'
		binTagsMap_verbs.put("sfm1fn", "MM-FH-NT-1P-FT");	// 'höfumst'
		binTagsMap_verbs.put("svm1fn", "MM-VH-NT-1P-FT");	// 'höfumst'
		binTagsMap_verbs.put("sbg2fn", "GM-BH-FT");			// 'verið'
		binTagsMap_verbs.put("sfg2fn", "GM-FH-NT-2P-FT");	// 'eruð'
		binTagsMap_verbs.put("svg2fn", "GM-VH-NT-2P-FT");	// 'séuð'
		binTagsMap_verbs.put("sbm2fn", "MM-BH-FT");			// 'aðhafist'
		binTagsMap_verbs.put("sfm2fn", "MM-FH-NT-2P-FT");	// 'hafist'
		binTagsMap_verbs.put("svm2fn", "MM-VH-NT-2P-FT");	// 'hafist'
		binTagsMap_verbs.put("sfg3fn", "GM-FH-NT-3P-FT");	// 'eru'
		binTagsMap_verbs.put("svg3fn", "GM-VH-NT-3P-FT");	// 'séu'
		binTagsMap_verbs.put("sfm3fn", "MM-FH-NT-3P-FT");	// 'hafast'
		binTagsMap_verbs.put("svm3fn", "MM-VH-NT-3P-FT");	// 'hafist'
		binTagsMap_verbs.put("sfg1eþ", "GM-FH-ÞT-1P-ET");	// 'var'
		binTagsMap_verbs.put("svg1eþ", "GM-VH-ÞT-1P-ET");	// 'væri'
		binTagsMap_verbs.put("sfm1eþ", "MM-FH-ÞT-1P-ET");	// 'hafðist'
		binTagsMap_verbs.put("svm1eþ", "MM-VH-ÞT-1P-ET");	// 'hefðist'
		binTagsMap_verbs.put("sfg2eþ", "GM-FH-ÞT-2P-ET");	// 'varst'
		binTagsMap_verbs.put("svg2eþ", "GM-VH-ÞT-2P-ET");	// 'værir'
		binTagsMap_verbs.put("sfm2eþ", "MM-FH-ÞT-2P-ET");	// 'hafðist'
		binTagsMap_verbs.put("svm2eþ", "MM-VH-ÞT-2P-ET");	// 'hefðist'
		binTagsMap_verbs.put("sfg3eþ", "GM-FH-ÞT-3P-ET");	// 'var'
		binTagsMap_verbs.put("svg3eþ", "GM-VH-ÞT-3P-ET");	// 'væri'
		binTagsMap_verbs.put("sfm3eþ", "MM-FH-ÞT-3P-ET");	// 'hafðist'
		binTagsMap_verbs.put("svm3eþ", "MM-VH-ÞT-3P-ET");	// 'hefðist'
		binTagsMap_verbs.put("sfg1fþ", "GM-FH-ÞT-1P-FT");	// 'vorum'
		binTagsMap_verbs.put("svg1fþ", "GM-VH-ÞT-1P-FT");	// 'værum'
		binTagsMap_verbs.put("sfm1fþ", "MM-FH-ÞT-1P-FT");	// 'höfðumst'
		binTagsMap_verbs.put("svm1fþ", "MM-VH-ÞT-1P-FT");	// 'hefðumst'
		binTagsMap_verbs.put("sfg2fþ", "GM-FH-ÞT-2P-FT");	// 'voruð'
		binTagsMap_verbs.put("svg2fþ", "GM-VH-ÞT-2P-FT");	// 'væruð'
		binTagsMap_verbs.put("sfm2fþ", "MM-FH-ÞT-2P-FT");	// 'höfðust'
		binTagsMap_verbs.put("svm2fþ", "MM-VH-ÞT-2P-FT");	// 'hefðust'
		binTagsMap_verbs.put("sfg3fþ", "GM-FH-ÞT-3P-FT");	// 'voru'
		binTagsMap_verbs.put("svg3fþ", "GM-VH-ÞT-3P-FT");	// 'væru'
		binTagsMap_verbs.put("sfm3fþ", "MM-FH-ÞT-3P-FT");	// 'höfðust'
		binTagsMap_verbs.put("svm3fþ", "MM-VH-ÞT-3P-FT");	// 'hefðust'
		
		//past participle
		binTagsMap_verbs.put("sþgken", "LHÞT-(S|V)B-KK-NFET");
		binTagsMap_verbs.put("sþmken", "MM-SAGNB");
		binTagsMap_verbs.put("sþgven", "LHÞT-(S|V)B-KVK-NFET");
		binTagsMap_verbs.put("sþmven", "MM-SAGNB");
		binTagsMap_verbs.put("sþghen", "LHÞT-(S|V)B-HK-NFET");
		binTagsMap_verbs.put("sþmhen", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkfn", "LHÞT-(S|V)B-KK-NFFT");
		binTagsMap_verbs.put("sþmkfn", "MM-SAGNB");
		binTagsMap_verbs.put("sþgvfn", "LHÞT-(S|V)B-KVK-NFFT");
		binTagsMap_verbs.put("sþmvfn", "MM-SAGNB");
		binTagsMap_verbs.put("sþghfn", "LHÞT-(S|V)B-HK-NFFT");
		binTagsMap_verbs.put("sþmhfn", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkeo", "LHÞT-(S|V)B-KK-ÞFET");
		binTagsMap_verbs.put("sþmkeo", "MM-SAGNB");
		binTagsMap_verbs.put("sþgveo", "LHÞT-(S|V)B-KVK-ÞFET");
		binTagsMap_verbs.put("sþmveo", "MM-SAGNB");
		binTagsMap_verbs.put("sþgheo", "LHÞT-(S|V)B-HK-ÞFET");
		binTagsMap_verbs.put("sþmheo", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkfo", "LHÞT-(S|V)B-KK-ÞFFT");
		binTagsMap_verbs.put("sþmkfo", "MM-SAGNB");
		binTagsMap_verbs.put("sþgvfo", "LHÞT-(S|V)B-KVK-ÞFFT");
		binTagsMap_verbs.put("sþmvfo", "MM-SAGNB");
		binTagsMap_verbs.put("sþghfo", "LHÞT-(S|V)B-HK-ÞFFT");
		binTagsMap_verbs.put("sþmhfo", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkeþ", "LHÞT-(S|V)B-KK-ÞGFET");
		binTagsMap_verbs.put("sþmkeþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþgveþ", "LHÞT-(S|V)B-KVK-ÞGFET");
		binTagsMap_verbs.put("sþmveþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþgheþ", "LHÞT-(S|V)B-HK-ÞGFET");
		binTagsMap_verbs.put("sþmheþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkfþ", "LHÞT-(S|V)B-KK-ÞGFFT");
		binTagsMap_verbs.put("sþmkfþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþgvfþ", "LHÞT-(S|V)B-KVK-ÞGFFT");
		binTagsMap_verbs.put("sþmvfþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþghfþ", "LHÞT-(S|V)B-HK-ÞGFFT");
		binTagsMap_verbs.put("sþmhfþ", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkee", "LHÞT-(S|V)B-KK-EFET");
		binTagsMap_verbs.put("sþmkee", "MM-SAGNB");
		binTagsMap_verbs.put("sþgvee", "LHÞT-(S|V)B-KVK-EFET");
		binTagsMap_verbs.put("sþmvee", "MM-SAGNB");
		binTagsMap_verbs.put("sþghee", "LHÞT-(S|V)B-HK-EFET");
		binTagsMap_verbs.put("sþmhee", "MM-SAGNB");
		binTagsMap_verbs.put("sþgkfe", "LHÞT-(S|V)B-KK-EFFT");
		binTagsMap_verbs.put("sþmkfe", "MM-SAGNB");
		binTagsMap_verbs.put("sþgvfe", "LHÞT-(S|V)B-KVK-EFFT");
		binTagsMap_verbs.put("sþmvfe", "MM-SAGNB");
		binTagsMap_verbs.put("sþghfe", "LHÞT-(S|V)B-HK-EFFT");
		binTagsMap_verbs.put("sþmhfe", "MM-SAGNB");
		//other verbs
		binTagsMap_verbs.put("slg", "LH-NT");		// 'hafandi'
		binTagsMap_verbs.put("sng", "GM-NH");		// infinitive, active 'vera'
		binTagsMap_verbs.put("ssg", "GM-SAGNB");	// 'haft'
		//binTagsMap_verbs.put("slm", "");
		binTagsMap_verbs.put("snm", "MM-NH");		// 'hafast'
		binTagsMap_verbs.put("ssm", "MM-SAGNB");	// 'hafst'
		
	}
	
	public static Map<String, String> binTagsMap_adj;
	
	static {
		binTagsMap_adj = new HashMap<String, String>();
		// l[kvh][ef][noþe][svo][fme]
		binTagsMap_adj.put("lkensf", "FSB-KK-NFET");	// 'fallegur'
		binTagsMap_adj.put("lkeosf", "FSB-KK-ÞFET");	// 'fallegan'
		binTagsMap_adj.put("lkeþsf", "FSB-KK-ÞGFET");	// 'fallegum'
		binTagsMap_adj.put("lkeesf", "FSB-KK-EFET");	// 'fallegs'
		
		binTagsMap_adj.put("lkfnsf", "FSB-KK-NFFT");	// 'fallegir'
		binTagsMap_adj.put("lkfosf", "FSB-KK-ÞFFT");	// 'fallega'
		binTagsMap_adj.put("lkfþsf", "FSB-KK-ÞGFFT");	// 'fallegum'
		binTagsMap_adj.put("lkfesf", "FSB-KK-EFFT");	// 'fallegra'
		
		binTagsMap_adj.put("lvensf", "FSB-KVK-NFET");	// 'falleg'
		binTagsMap_adj.put("lveosf", "FSB-KVK-ÞFET");	// 'fallega'
		binTagsMap_adj.put("lveþsf", "FSB-KVK-ÞGFET");	// 'fallegri'
		binTagsMap_adj.put("lveesf", "FSB-KVK-EFET");	// 'fallegrar'
		
		binTagsMap_adj.put("lvfnsf", "FSB-KVK-NFFT");	// 'fallegar'
		binTagsMap_adj.put("lvfosf", "FSB-KVK-ÞFFT");	// 'fallegar'
		binTagsMap_adj.put("lvfþsf", "FSB-KVK-ÞGFFT");	// 'fallegum'
		binTagsMap_adj.put("lvfesf", "FSB-KVK-EFFT");	// 'fallegra'
		
		binTagsMap_adj.put("lhensf", "FSB-HK-NFET");	// 'fallegt'
		binTagsMap_adj.put("lheosf", "FSB-HK-ÞFET");	// 'fallegt'
		binTagsMap_adj.put("lheþsf", "FSB-HK-ÞGFET");	// 'fallegu'
		binTagsMap_adj.put("lheesf", "FSB-HK-EFET");	// 'fallegs'
		
		binTagsMap_adj.put("lhfnsf", "FSB-HK-NFFT");	// 'falleg'
		binTagsMap_adj.put("lhfosf", "FSB-HK-ÞFFT");	// 'fallegra'
		binTagsMap_adj.put("lhfþsf", "FSB-HK-ÞGFFT");	// 'fallegum'
		binTagsMap_adj.put("lhfesf", "FSB-HK-EFFT");	// 'fallegra'
		
		binTagsMap_adj.put("lkenvf", "FVB-KK-NFET");	// 'fallegi'
		binTagsMap_adj.put("lkeovf", "FVB-KK-ÞFET");	// 'fallega'
		binTagsMap_adj.put("lkeþvf", "FVB-KK-ÞGFET");	// 'fallega'
		binTagsMap_adj.put("lkeevf", "FVB-KK-EFET");	// 'fallega'
		
		binTagsMap_adj.put("lkfnvf", "FVB-KK-NFFT");	// 'fallegu'
		binTagsMap_adj.put("lkfovf", "FVB-KK-ÞFFT");	// 'fallegu'
		binTagsMap_adj.put("lkfþvf", "FVB-KK-ÞGFFT");	// 'fallegu'
		binTagsMap_adj.put("lkfevf", "FVB-KK-EFFT");	// 'fallegu'
		
		binTagsMap_adj.put("lvenvf", "FVB-KVK-NFET");	// 'fallegu'
		binTagsMap_adj.put("lveovf", "FVB-KVK-ÞFET");	// 'fallegu'
		binTagsMap_adj.put("lveþvf", "FVB-KVK-ÞGFET");	// 'fallegu'
		binTagsMap_adj.put("lveevf", "FVB-KVK-EFET");	// 'fallegu'
		
		binTagsMap_adj.put("lvfnvf", "FVB-KVK-NFFT");	// 'fallegu'
		binTagsMap_adj.put("lvfovf", "FVB-KVK-ÞFFT");	// 'fallegu'
		binTagsMap_adj.put("lvfþvf", "FVB-KVK-ÞGFFT");	// 'fallegu'
		binTagsMap_adj.put("lvfevf", "FVB-KVK-EFFT");	// 'fallegu'
		
		binTagsMap_adj.put("lhenvf", "FVB-HK-NFET");	// 'fallega'
		binTagsMap_adj.put("lheovf", "FVB-HK-ÞFET");	// 'fallega'
		binTagsMap_adj.put("lheþvf", "FVB-HK-ÞGFET");	// 'fallega'
		binTagsMap_adj.put("lheevf", "FVB-HK-EFET");	// 'fallega'
		
		binTagsMap_adj.put("lhfnvf", "FVB-HK-NFFT");	// 'fallegu'
		binTagsMap_adj.put("lhfovf", "FVB-HK-ÞFFT");	// 'fallegu'
		binTagsMap_adj.put("lhfþvf", "FVB-HK-ÞGFFT");	// 'fallegu'
		binTagsMap_adj.put("lhfevf", "FVB-HK-EFFT");	// 'fallegu'
		
		binTagsMap_adj.put("lkenvm", "MST-KK-NFET");	// 'fallegri'
		binTagsMap_adj.put("lkeovm", "MST-KK-ÞFET");	// 'fallegri'
		binTagsMap_adj.put("lkeþvm", "MST-KK-ÞGFET");	// 'fallegri'
		binTagsMap_adj.put("lkeevm", "MST-KK-EFET");	// 'fallegri'
		
		binTagsMap_adj.put("lkfnvm", "MST-KK-NFFT");	// 'fallegri'
		binTagsMap_adj.put("lkfovm", "MST-KK-ÞFFT");	// 'fallegri'
		binTagsMap_adj.put("lkfþvm", "MST-KK-ÞGFFT");	// 'fallegri'
		binTagsMap_adj.put("lkfevm", "MST-KK-EFFT");	// 'fallegri'
		
		binTagsMap_adj.put("lvenvm", "MST-KVK-NFET");	// 'fallegri'
		binTagsMap_adj.put("lveovm", "MST-KVK-ÞFET");	// 'fallegri'
		binTagsMap_adj.put("lveþvm", "MST-KVK-ÞGFET");	// 'fallegri'
		binTagsMap_adj.put("lveevm", "MST-KVK-EFET");	// 'fallegri'
		
		binTagsMap_adj.put("lvfnvm", "MST-KVK-NFFT");	// 'fallegri'
		binTagsMap_adj.put("lvfovm", "MST-KVK-ÞFFT");	// 'fallegri'
		binTagsMap_adj.put("lvfþvm", "MST-KVK-ÞGFFT");	// 'fallegri'
		binTagsMap_adj.put("lvfevm", "MST-KVK-EFFT");	// 'fallegri'
		
		binTagsMap_adj.put("lhenvm", "MST-HK-NFET");	// 'fallegra'
		binTagsMap_adj.put("lheovm", "MST-HK-ÞFET");	// 'fallegra'
		binTagsMap_adj.put("lheþvm", "MST-HK-ÞGFET");	// 'fallegra'
		binTagsMap_adj.put("lheevm", "MST-HK-EFET");	// 'fallegra'
		
		binTagsMap_adj.put("lhfnvm", "MST-HK-NFFT");	// 'fallegri'
		binTagsMap_adj.put("lhfovm", "MST-HK-ÞFFT");	// 'fallegri'
		binTagsMap_adj.put("lhfþvm", "MST-HK-ÞGFFT");	// 'fallegri'
		binTagsMap_adj.put("lhfevm", "MST-HK-EFFT");	// 'fallegri'
		
		binTagsMap_adj.put("lkense", "ESB-KK-NFET");	// 'fallegastur'
		binTagsMap_adj.put("lkeose", "ESB-KK-ÞFET");	// 'fallegastan'
		binTagsMap_adj.put("lkeþse", "ESB-KK-ÞGFET");	// 'fallegustum'
		binTagsMap_adj.put("lkeese", "ESB-KK-EFET");	// 'fallegasts'
		
		binTagsMap_adj.put("lkfnse", "ESB-KK-NFFT");	// 'fallegastir'
		binTagsMap_adj.put("lkfose", "ESB-KK-ÞFFT");	// 'fallegasta'
		binTagsMap_adj.put("lkfþse", "ESB-KK-ÞGFFT");	// 'fallegustum'
		binTagsMap_adj.put("lkfese", "ESB-KK-EFFT");	// 'fallegastra'
		
		binTagsMap_adj.put("lvense", "ESB-KVK-NFET");	// 'fallegust'
		binTagsMap_adj.put("lveose", "ESB-KVK-ÞFET");	// 'fallegasta'
		binTagsMap_adj.put("lveþse", "ESB-KVK-ÞGFET");	// 'fallegastri'
		binTagsMap_adj.put("lveese", "ESB-KVK-EFET");	// 'fallegastrar'
		
		binTagsMap_adj.put("lvfnse", "ESB-KVK-NFFT");	// 'fallegastar'
		binTagsMap_adj.put("lvfose", "ESB-KVK-ÞFFT");	// 'fallegastar'
		binTagsMap_adj.put("lvfþse", "ESB-KVK-ÞGFFT");	// 'fallegustum'
		binTagsMap_adj.put("lvfese", "ESB-KVK-EFFT");	// 'fallegastra'
		
		binTagsMap_adj.put("lhense", "ESB-HK-NFET");	// 'fallegast'
		binTagsMap_adj.put("lheose", "ESB-HK-ÞFET");	// 'fallegast'
		binTagsMap_adj.put("lheþse", "ESB-HK-ÞGFET");	// 'fallegustu'
		binTagsMap_adj.put("lheese", "ESB-HK-EFET");	// 'fallegasts'
		
		binTagsMap_adj.put("lhfnse", "ESB-HK-NFFT");	// 'fallegust'
		binTagsMap_adj.put("lhfose", "ESB-HK-ÞFFT");	// 'fallegust'
		binTagsMap_adj.put("lhfþse", "ESB-HK-ÞGFFT");	// 'fallegustum'
		binTagsMap_adj.put("lhfese", "ESB-HK-EFFT");	// 'fallegastra'
		
		binTagsMap_adj.put("lkenve", "EVB-KK-NFET");	// 'fallegasti'
		binTagsMap_adj.put("lkeove", "EVB-KK-ÞFET");	// 'fallegasta'
		binTagsMap_adj.put("lkeþve", "EVB-KK-ÞGFET");	// 'fallegasta'
		binTagsMap_adj.put("lkeeve", "EVB-KK-EFET");	// 'fallegasta'
		
		binTagsMap_adj.put("lkfnve", "EVB-KK-NFFT");	// 'fallegustu'
		binTagsMap_adj.put("lkfove", "EVB-KK-ÞFFT");	// 'fallegustu'
		binTagsMap_adj.put("lkfþve", "EVB-KK-ÞGFFT");	// 'fallegustu'
		binTagsMap_adj.put("lkfeve", "EVB-KK-EFFT");	// 'fallegustu'
		
		binTagsMap_adj.put("lvenve", "EVB-KVK-NFET");	// 'fallegustu'
		binTagsMap_adj.put("lveove", "EVB-KVK-ÞFET");	// 'fallegustu'
		binTagsMap_adj.put("lveþve", "EVB-KVK-ÞGFET");	// 'fallegustu'
		binTagsMap_adj.put("lveeve", "EVB-KVK-EFET");	// 'fallegustu'
		
		binTagsMap_adj.put("lvfnve", "EVB-KVK-NFFT");	// 'fallegustu'
		binTagsMap_adj.put("lvfove", "EVB-KVK-ÞFFT");	// 'fallegustu'
		binTagsMap_adj.put("lvfþve", "EVB-KVK-ÞGFFT");	// 'fallegustu'
		binTagsMap_adj.put("lvfeve", "EVB-KVK-EFFT");	// 'fallegustu'
		
		binTagsMap_adj.put("lhenve", "EVB-HK-NFET");	// 'fallegasta'
		binTagsMap_adj.put("lheove", "EVB-HK-ÞFET");	// 'fallegasta'
		binTagsMap_adj.put("lheþve", "EVB-HK-ÞGFET");	// 'fallegasta'
		binTagsMap_adj.put("lheeve", "EVB-HK-EFET");	// 'fallegasta'
		
		binTagsMap_adj.put("lhfnve", "EVB-HK-NFFT");	// 'fallegustu'
		binTagsMap_adj.put("lhfove", "EVB-HK-ÞFFT");	// 'fallegustu'
		binTagsMap_adj.put("lhfþve", "EVB-HK-ÞGFFT");	// 'fallegustu'
		binTagsMap_adj.put("lhfeve", "EVB-HK-EFFT");	// 'fallegustu'
	}

}
