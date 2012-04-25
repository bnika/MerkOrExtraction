package util;

import static org.junit.Assert.*;

import java.util.Arrays;

import is.merkor.util.MerkorCSVFile;

import org.junit.Before;
import org.junit.Test;

public class MerkorCSVFileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMerkorCSVFile() {
		String[] tabTestLine_1 = {"this", "is", "a", "test"};
		String[] tabTestLine_2 = {"file", "for", "merkor", "csv"};
		String[] tabTestLine_3 = {"reader", "using", "tab", "separation"};
		
		MerkorCSVFile file = new MerkorCSVFile("/Users/anna/EclipseProjects/workspace/MerkOrExtraction/test/util/csv_tab_test.csv", '\t');
		int counter = 0;
		for(String[] line : file) {
			counter++;
			if(counter == 1)
				assertTrue(Arrays.equals(tabTestLine_1, line));
			else if (counter == 2)
				assertTrue(Arrays.equals(tabTestLine_2,line));
			else if (counter == 3)
				assertTrue(Arrays.equals(tabTestLine_3,line));
			else
				fail();
//			for(String str : line)
//				System.out.println(str.toString());
		}
		String[] commaTestLine_1 = {"this", "is", "a", "test"};
		String[] commaTestLine_2 = {"file", "for", "merkor", "csv"};
		String[] commaTestLine_3 = {"reader", "using", "comma", "separation"};
		file = new MerkorCSVFile("/Users/anna/EclipseProjects/workspace/MerkOrExtraction/test/util/csv_comma_test.csv", ',');
		counter = 0;
		for(String[] line : file) {
			counter++;
			if(counter == 1)
				assertTrue(Arrays.equals(commaTestLine_1, line));
			else if (counter == 2)
				assertTrue(Arrays.equals(commaTestLine_2,line));
			else if (counter == 3)
				assertTrue(Arrays.equals(commaTestLine_3,line));
			else
				fail();
			
//			for(String str : line)
//				System.out.println(str.toString());
		}
			
	}

}
