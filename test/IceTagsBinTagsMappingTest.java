
import static org.junit.Assert.*;
import is.merkor.preprocessing.IceTagsBinTagsMapping;
import is.merkor.preprocessing.WordIceBin;
import is.merkor.util.MerkorTokenReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class IceTagsBinTagsMappingTest {
	List<String> testList;

	@Before
	public void setUp() throws Exception {
		testList = new ArrayList<String>();
		
		testList.add("Frá");
		testList.add("nken-s");
		testList.add("og");
		testList.add("c");
		testList.add("með");
		testList.add("aþ");
		testList.add("15");
		testList.add("ta");
		testList.add("september");
		testList.add("nkeþ");
		testList.add("2005");
		testList.add("ta");
		testList.add("vantar");
		testList.add("sfg3en");
		testList.add("okkur");
		testList.add("fp1fo");
		testList.add("starfsmann-");
		testList.add("nkeo");
		testList.add("í");
		testList.add("aþ");
		testList.add("aafgreiðslu");
		testList.add("nveþ");
		testList.add(".");
		testList.add(".");
		testList.add("Eingöngu");
		testList.add("aa");
		testList.add("er");
		testList.add("sfg3en");
	}
	
	@Test
	public void testNounMapping() {
		IceTagsBinTagsMapping mapper = new IceTagsBinTagsMapping("nouns");
		
		for (String token : testList) {
			mapper.process(token);
		}
		
		WordIceBin testWord_1 = new WordIceBin("Frá", "nken-s", "");
		assertEquals(testWord_1, mapper.getWordList().get(0));
		WordIceBin testWord_2 = new WordIceBin("september", "nkeþ", "ÞGFET");
		assertEquals(testWord_2, mapper.getWordList().get(1));
		
		String nonValid_1 = "starfsmann-";
		String nonValid_2 = "aafgreiðslu";
		
		assertEquals(nonValid_1, mapper.getNonWordList().get(0));
		assertEquals(nonValid_2, mapper.getNonWordList().get(1));
		
	}
	
	@Test
	public void testVerbMapping () {
		IceTagsBinTagsMapping mapper = new IceTagsBinTagsMapping("verb");
		
		for (String token : testList) {
			mapper.process(token);
		}
		
		WordIceBin testWord_3 = new WordIceBin("vantar", "sfg3en", "GM-FH-NT-3P-ET");
		assertEquals(testWord_3, mapper.getWordList().get(0));
		
	}
}
