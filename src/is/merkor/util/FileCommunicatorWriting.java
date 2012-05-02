package is.merkor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileCommunicatorWriting {
	
	/**
	 * Appends a line to a file
	 * @param filename the name of a file to which the line should be appended to
	 * @param line the line to be written
	 */
	public static void writeLineAppend (String filename, String line) {
		try {
			BufferedWriter out = createWriter(filename, true);
			out.write(line);
			out.write("\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Writes a List of Strings to a file, overrides if file exists (non append)
	 * @param filename file to write the list into
	 * @param list the list containing the strings to write
	 */
	public static void writeListNonAppend (String filename, List<String> list) {
		try {
			BufferedWriter out = createWriter(filename, false);
			for(String s : list) {
				out.write(s);
				out.write("\n");
			}
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Writes a list of strings to file {@code filename}, appending to the
	 * file if it exists.
	 * 
	 * @param list
	 * @param filename
	 */
	public static void writeListAppend (List<String> list, String filename) {
		try {
			BufferedWriter out = createWriter(filename, true);
			for(String s : list) {
				out.write(s);
				out.write("\n");
			}
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public static void writeBufferToFile (StringBuffer text, String filename) {
		try {
			BufferedWriter out = createWriter(filename, false);
			out.write(text.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeBufferToFileAppend(StringBuffer text, String filename) {
		try {
			BufferedWriter out = createWriter(filename, true);
			out.write(text.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static BufferedWriter createWriter (String filename, boolean append) throws IOException {
		 return (new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, append), "UTF8")));
	}
	
	public static void createDirIfNotExist (String dirName) {
		File dir = new File(dirName);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdir();
		}
	}
	
	//// Methods from merkor.hg -> to refactor!!
	
	public static void writeRelation(String word1, String word2, String relation, String coveredText) {
		//filter out some none valid words
		if(word1.startsWith(".") || word2.startsWith("."))
			return;
		//set all relations including proper nouns to "proper"
		//TODO: this is not good, also sets all words containing '-' to proper!!
		// doesn't need - already set to proper in RelationInitializer!
		//if(coveredText.contains("-") || coveredText.matches(".*n\\S\\S\\Sgs"))
		//	relation = "proper";
		//no newlines wanted in the output coveredText
		coveredText = coveredText.replaceAll("\n", " ");
		createDirIfNotExist("relationDetectorResults");
		try {
			BufferedWriter out = createWriter("relationDetectorResults/" + relation + ".csv", true);
			//BufferedWriter out = createWriter("relationResults/" + relation + ".csv", true);
			out.write(word1);
			out.write("\t");
			out.write(word2);
			out.write("\t");
			out.write(coveredText);
			out.write("\n");
			
			out.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	

	
}

