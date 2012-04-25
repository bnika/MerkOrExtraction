package preprocessing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import is.merkor.preprocessing.BINLemmatizer;

import org.junit.Before;
import org.junit.Test;

public class BINLemmatizerTest {
	List<String> testList;
	
	@Before
	public void setUp() throws Exception {
		testList = new ArrayList<String>();
		
//		testList.add("Frá");
//		testList.add("nken-s");
//		testList.add("og");
//		testList.add("c");
//		testList.add("með");
//		testList.add("aþ");
//		testList.add("15");
//		testList.add("ta");
//		testList.add("september");
//		testList.add("nkeþ");
//		testList.add("2005");
//		testList.add("ta");
//		testList.add("vantar");
//		testList.add("sfg3en");
//		testList.add("okkur");
//		testList.add("fp1fo");
//		testList.add("starfsmann-");
//		testList.add("nkeo");
//		testList.add("í");
//		testList.add("aþ");
//		testList.add("aafgreiðslu");
//		testList.add("nveþ");
//		testList.add(".");
//		testList.add(".");
//		testList.add("Eingöngu");
//		testList.add("aa");
//		testList.add("er");
//		testList.add("sfg3en");
		testList.add("hópur");
		testList.add("nken");
		testList.add("hóf");
		testList.add("sfg3eþ");
		testList.add("árið");
		testList.add("nheog");
	}

	@Test
	public void testProcess() {
		BINLemmatizer lemmatizer = new BINLemmatizer();
		
		for (String token : testList) {
			lemmatizer.process(token);
		}
		lemmatizer.finishProcess();
	}

}
