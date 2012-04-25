package is.merkor.util;


import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Iterable class using opencsv CSVReader.
 * 
 * Usage:
 * MerkorCSVFile file = new MerkorCSVFile("someFilename.csv", 'separatorChar');
 *
 * for(String[] line : file)
 * 	  doSomethingWith(line);
 *		   
 * @author Anna B. Nikulasdottir
 *
 */
public class MerkorCSVFile implements Iterable<String[]> {
	CSVReader reader;
	
	public MerkorCSVFile (String filename, char separator) {
		try {
			reader = new CSVReader(new FileReader(filename), separator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Iterator<String[]> iterator() {
		return new CSVFileIterator();
	}
	
	private class CSVFileIterator implements Iterator<String[]> {
		
		private String[] currentLine;
		
		@Override
		public boolean hasNext() {
			try {
				currentLine = reader.readNext();
			} catch (Exception e) {
				currentLine = null;
				e.printStackTrace();
			}
			return currentLine != null;
		}

		@Override
		public String[] next() {
			return currentLine;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub	
		}
	}
}
