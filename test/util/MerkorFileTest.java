package util;

import static org.junit.Assert.*;
import is.merkor.util.MerkorFile;

import org.junit.Before;
import org.junit.Test;

public class MerkorFileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMerkorFile() {
		try {
			MerkorFile file = new MerkorFile("/Users/anna/EclipseProjects/workspace/MerkOrExtraction/test/util/lemma_ari_olafsson_malheild.txt");
			assertTrue(file != null);
//			for (String line : file) {
//				System.out.println(line);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
