package patternextraction;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import is.merkor.patternextraction.PatternExtraction;
import is.merkor.patternextraction.PatternInfo;
import is.merkor.util.MerkorFile;

import org.junit.Before;
import org.junit.Test;

public class PatternExtractionTest {
	
	List<String> testArray;
	List<PatternInfo> testResults;
	
	@Before
	public void setUp() throws Exception {
		initializeTestArray();
		initializeTestResults();
	}
	
	@Test
	public void testProcessLine() {
		PatternExtraction extr = new PatternExtraction();
		for (String line : testArray)
			extr.processLine(line);
		
		List<PatternInfo> results = extr.getExtractedPatterns();
		
		assertEquals(results, testResults);
		
		
	}
	@Test
//	public void testProcessLineWithFileReading() {
//		PatternExtraction extr = new PatternExtraction();
//		try {
//			File inputDir = new File("/Users/anna/MERKOR/Data/SubMIM/SubMIM_parsed/");
//			if (inputDir.isDirectory()) {
//				File[] dirs = inputDir.listFiles();
//				for(int i = 0; i < dirs.length; i++) {
//					if(!dirs[i].getName().startsWith(".")) {
//						File[] files = dirs[i].listFiles();
//						for(int j = 0; j < files.length; j++) {
//							System.out.println("file nr. " + j);
//							if(!files[j].getName().startsWith(".")) {
//								MerkorFile current = new MerkorFile(files[j].getAbsolutePath());
//								for(String line : current) {
//									extr.processLine(line);
//								}
//							}
//						}
//					}
//				}	
//			}
//			
//			extr.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	public void initializeTestArray() {
		testArray =new ArrayList<String>();
		
		testArray.add("[NP Prófessor nken NP]");
		testArray.add("[NP Mennin nxee-s NP]");
		testArray.add("[VP sagði sfg3eþ VP]");
		testArray.add("[PP í aþ [NP samtali nheþ NP] PP]");
		testArray.add("[PP við ao [NP Læknablaðið nheogs NP] PP]");
		testArray.add("[SCP að c SCP]");
		testArray.add("[NP markmiðið nheng NP]");
		testArray.add("[PP með aþ [NP komu nveþ sinni feveþ NP] PP]");
		testArray.add("[AdvP hingað aa AdvP]");
		testArray.add("[VP væri svg3eþ VP]");
		testArray.add("[VPi að cn stuðla sng VPi]");
		testArray.add("[PP að aþ [NP kynningu nveþ NP] PP]");
		testArray.add("[PP innan ae [NP læknadeildar nvee NP] PP]");
		testArray.add("[PP á aþ [NPs [NP [AP ákveðnum lvfþsf AP] hugmyndum nvfþ NP] [CP og c CP] [NP aðferðum nvfþ NP] NPs] PP]");
		testArray.add("[SCP sem ct SCP]");
		testArray.add("[VPs beitt ssg VPs]");
		testArray.add("[VPb hefur sfg3en verið ssg VPb]");
		testArray.add("[PP við ao [NP kennslu nveo NP] PP]");
		testArray.add("[NP læknanema nkeþ NP]");
	}
	public void initializeTestResults() {
		testResults = new ArrayList<PatternInfo>();
		testResults.add(new PatternInfo("[NP nxen ] [NP nxee-s ]", "[NP Prófessor nken NP] [NP Mennin nxee-s NP]"));
		testResults.add(new PatternInfo("[PP í  aþ [NP nxeþ ]] [PP við  ao [NP nxeogs ]]", "[PP í aþ [NP samtali nheþ NP] PP] [PP við ao [NP Læknablaðið nheogs NP] PP]"));
		testResults.add(new PatternInfo("[NP nxeng ] [PP með  aþ [NP nxeþ  fexeþ ]]", "[NP markmiðið nheng NP] [PP með aþ [NP komu nveþ sinni feveþ NP] PP]"));
		testResults.add(new PatternInfo("[PP að  aþ [NP nxeþ ]] [PP innan  ae [NP nxee ]] [PP á  aþ [NPs[NP[AP lvfxsf ] nxfþ ][CP og  c ][NP nxfþ ]]]", 
				"[PP að aþ [NP kynningu nveþ NP] PP] [PP innan ae [NP læknadeildar nvee NP] PP] [PP á aþ [NPs [NP [AP ákveðnum lvfþsf AP] hugmyndum nvfþ NP] [CP og c CP] [NP aðferðum nvfþ NP] NPs] PP]"));
	}
}
