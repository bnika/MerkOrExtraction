package util;

import static org.junit.Assert.*;
import is.merkor.preprocessing.BINLemmatizer;
import is.merkor.util.MerkorTokenReader;

import org.junit.Before;
import org.junit.Test;

public class MerkorTokenReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadTokensFromFile() {
		BINLemmatizer lemmatizer = new BINLemmatizer();
		MerkorTokenReader reader = new MerkorTokenReader(lemmatizer);
		
		reader.readTokensFromFile("/Users/anna/EclipseProjects/workspace/MerkOrExtraction/test/util/mogginn_tagged.txt");
	}
}
