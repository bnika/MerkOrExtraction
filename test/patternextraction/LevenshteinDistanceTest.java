package patternextraction;

import static org.junit.Assert.*;
import is.merkor.patternextraction.LevenshteinDistance;

import org.junit.Before;
import org.junit.Test;

public class LevenshteinDistanceTest {
	
	String testStr_1 = "test string one";
	String testStr_2 = "test string two";
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetLevenshteinDistanceWordsZero() {
		LevenshteinDistance lev = new LevenshteinDistance();
		
		int dist = lev.getLevenshteinDistanceWordBased(testStr_1, testStr_1, true);
		
		assertTrue(dist == 0);
	}
	
	@Test
	public void testGetLevenshteinDistanceWordsNonZero() {
		LevenshteinDistance lev = new LevenshteinDistance();
		
		int dist = lev.getLevenshteinDistanceWordBased(testStr_1, testStr_2, true);
		//System.out.println(dist);
		assertTrue(dist == 1);
	}
	
	@Test
	public void testGetLevenshteinDistanceZero() {
		LevenshteinDistance lev = new LevenshteinDistance();
		
		int dist = lev.getLevenshteinDistance(testStr_1, testStr_1, true);
		
		assertTrue(dist == 0);
	}
	
	@Test
	public void testGetLevenshteinDistanceNonZero() {
		LevenshteinDistance lev = new LevenshteinDistance();
		
		int dist = lev.getLevenshteinDistance(testStr_1, testStr_2, true);
		//System.out.println(dist);
		assertTrue(dist == 3);
	}

}
