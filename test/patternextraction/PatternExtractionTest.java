package patternextraction;

import static org.junit.Assert.*;

import java.io.File;

import is.merkor.patternextraction.PatternExtraction;
import is.merkor.util.MerkorFile;

import org.junit.Before;
import org.junit.Test;

public class PatternExtractionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcessLine() {
		PatternExtraction extr = new PatternExtraction("SubMIM_patternsOut.csv");
		try {
			File inputDir = new File("/Users/anna/MERKOR/Data/SubMIM/SubMIM_parsed/");
			if (inputDir.isDirectory()) {
				File[] dirs = inputDir.listFiles();
				for(int i = 0; i < dirs.length; i++) {
					if(!dirs[i].getName().startsWith(".")) {
						File[] files = dirs[i].listFiles();
						for(int j = 0; j < files.length; j++) {
							System.out.println("file nr. " + j);
							if(!files[j].getName().startsWith(".")) {
								MerkorFile current = new MerkorFile(files[j].getAbsolutePath());
								for(String line : current) {
									extr.processLine(line);
								}
							}
						}
					}
				}	
			}
			
			extr.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
