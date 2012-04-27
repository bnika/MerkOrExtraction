package patternextraction;

import static org.junit.Assert.*;
import is.merkor.patternextraction.PatternGeneralizator;

import org.junit.Before;
import org.junit.Test;

public class PatternGeneralizatorTest {
	
	String testStr_1 = "test string one two one";
	String testStr_2 = "test string two two two";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGeneralizePattern() {
		PatternGeneralizator gen = new PatternGeneralizator();
		
		String genPat = gen.generalizePattern(testStr_1, testStr_2);
		System.out.println(genPat);
	}

}
