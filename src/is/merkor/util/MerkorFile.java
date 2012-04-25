package is.merkor.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Handles reading from file line by line.
 * Idea taken from : http://code.hammerpig.com/how-to-read-really-large-files-in-java.html (28.03.2012)
 * 
 * Usage example:
 * 
 * MerkorFile file = new MerkorFile("someFilePath");
 * for(String line : file)
 * 	  doSomethingWith(line);
 *
 */
public class MerkorFile implements Iterable<String> {
	
	private BufferedReader reader = null;
	
	public MerkorFile (String filename) {
		try {
			reader = FileCommunicatorReading.createReader(filename);
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
	public Iterator<String> iterator() {
		return new FileIterator();
	}
	
	private class FileIterator implements Iterator<String> {
		
		private String currentLine;
		
		@Override
		public boolean hasNext() {
			try {
				currentLine = reader.readLine();
			} catch (Exception e) {
				currentLine = null;
				e.printStackTrace();
			}
			return currentLine != null;
		}

		@Override
		public String next() {
			return currentLine;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub	
		}
		
	}
	
}
