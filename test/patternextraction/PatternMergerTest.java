package patternextraction;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import is.merkor.patternextraction.PatternInfo;
import is.merkor.patternextraction.PatternMerger;

import org.junit.Before;
import org.junit.Test;

public class PatternMergerTest {
	
	PatternMerger merger;
	List<PatternInfo> testPatterns;
	List<String> testResults;
	
	@Before
	public void setUp() throws Exception {
		merger = new PatternMerger();
		initTestPatterns();
		initTestResults();
	}
	
	@Test
	public void processTest () {
		List<String> mergedPatts = merger.process_test("genitive", testPatterns);
		
		assertTrue(mergedPatts.size() == testResults.size());
		
		for (String pat : mergedPatts) {
			assertTrue(testResults.contains(pat));
		}
	}
	
	public void initTestPatterns () {
		testPatterns = new ArrayList<PatternInfo>();
		
		testPatterns.add(new PatternInfo("[PP á  aþ [NP nxeþ ][NP nxeeg ]]", ""));
		testPatterns.add(new PatternInfo("[NP nxen ] [NP nxee ]", ""));
		testPatterns.add(new PatternInfo("[NP nxen ] [NP nxeeg ]", ""));
		testPatterns.add(new PatternInfo("[PP að  aþ [NP nxeþ ][NP nxfegö ]]", ""));
		testPatterns.add(new PatternInfo("[NP nxeo ] [PP í  ao [NP nxeo ][NP nxee-m ]]", ""));
		testPatterns.add(new PatternInfo("[NP nxen ] [NP[AP lkfxsf ] nxfe ]", ""));
		testPatterns.add(new PatternInfo("[NP nxeo ] [NP nxeeg ]", ""));
		testPatterns.add(new PatternInfo("[NP nxen ] [NP nxfe ]", ""));
		testPatterns.add(new PatternInfo("[PP um  ao [NP nxeo ][NP nxeeg ]]", ""));
		testPatterns.add(new PatternInfo("[PP í  aþ [NP nxeþ ][NP nxee-ö ]]", ""));
		testPatterns.add(new PatternInfo("[PP Í  aþ [NP nxeþ ][NP nxeeg ]]", ""));
		
		
	}
	public void initTestResults () {
		testResults = new ArrayList<String>();
		
		testResults.add("[np nn ] [np[ap l ] ne ]");
		testResults.add("[np no ] [pp í  ao [np no ][np ne-s ]]");
		testResults.add("[pp að  aþ [np nþ ][np negö ]]");
		testResults.add("[pp um  ao [np no ][np neg ]]");
		testResults.add("[pp á  aþ [np nþ ][np neg ]]");
		testResults.add("[np nn ] [np ne|neg ]");
		testResults.add("[np nn|no ] [np neg ]");
		testResults.add("[pp í  aþ [np nþ ][np ne-s|neg ]]");	          

	}

}
