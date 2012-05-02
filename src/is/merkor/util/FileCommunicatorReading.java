package is.merkor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCommunicatorReading {
	
	/**
	 * Creates and returns a bufferedReader, utf-8, on {@code filename}.
	 
	 * @param filename
	 * @return a bufferedReader
	 * @throws IOException if reading fails
	 */
	public static BufferedReader createReader (String filename) throws IOException {
		 return (new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8")));
	}
	
	/**
	 * Reads lines from {@code filename} and returns a List of the lines as Strings.
	 * Omits empty lines.
	 * 
	 * @param filename name of the file to read from
	 * @return the file content as List of Strings (lines)
	 */
	public static List<String> readListFromFile(String filename) {
		List<String> stringList = new ArrayList<String>();
		try {
			BufferedReader in = createReader(filename);
			String line = "";
			while ((line = in.readLine()) != null) {
				if (!line.isEmpty())
					stringList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringList;
	}
	/**
	 * Reads words from {@code filename} and returns a List of the words as Strings.
	 * 
	 * @param filename name of the file to read from
	 * @return the file content as List of Strings (words)
	 */
	public static List<String> getWordsFromFileAsList(String filename) {
		List<String> stringList = new ArrayList<String>();
		try {
			BufferedReader in = createReader(filename);
			String line = "";
			String[] words;
			while ((line = in.readLine()) != null) {
				if (!line.isEmpty()) {
					words = line.split(" ");
					for (int i = 0; i < words.length; i++)
						stringList.add(words[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringList;
	}
	
	/**
	 * Reads the first two columns of a tab separated file into a map<String, String>.
	 * The input file has to contain at least two columns separated by a <code>"\t"</code>
	 * @param filename the filename of the input file
	 * @return a map<String, String> containing the first two columns of the input file
	 */
	public static Map<String, String> getStringStringMapFromFile(String filename) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			BufferedReader in = createReader(filename);
			String line = "";
			String[] lineArr;
			while ((line = in.readLine()) != null) {
				lineArr = line.split("\t");
				if (lineArr.length < 2) {
					continue;
				}
				map.put(lineArr[0], lineArr[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Create an Array of files contained in a directory
	 * 
	 * @param directory the directory to extract files from
	 * @return an Array of files in the parametar directory
	 */
	public static File[] getFileList (String directory) {
		File dir = new File(directory);
		File[] files = dir.listFiles();
		return files;
	}

}
