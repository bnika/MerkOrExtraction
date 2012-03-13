package is.merkor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * This class reads files from a directory and uses <code>StreamTokenizer</code>
 * to feed any processing class with the file content token by token.
 * 
 * @author Anna B. Nikulasdottir
 *
 */
public class MerkorTokenReader {
	
	private ProcessingClass processingClass;
	
	/**
	 * Initialize processingClass, which is going to process the tokens
	 * read by this class.
	 */
	public MerkorTokenReader (ProcessingClass processingClass) {
		this.processingClass = processingClass;
	}
	
	/**
	 * Reads tokens from file and calls process(token) for each token.
	 * 
	 * @param filename
	 */
	public void readTokensFromFile (String filename) {
		
		try {
			System.out.println("creating reader for " + filename + "...");
			BufferedReader in = FileCommunicatorReading.createReader(filename);
			System.out.println("creating tokenizer ...");
			StreamTokenizer tokenizer = new StreamTokenizer(in);
			tokenizer.lowerCaseMode(true);
			int linenr = 0;
			while((tokenizer.nextToken() != StreamTokenizer.TT_EOF)) {
				linenr = tokenizer.lineno();
				if(linenr%1000 == 0) {
					System.out.print("\rTokenizer line: " + linenr);
				}
				if((tokenizer.ttype == StreamTokenizer.TT_WORD)) {
					//System.out.println(tokenizer.sval);
					process(tokenizer.sval);
				}
			}
			in.close();
			finishProcess();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void process(String word) {
		processingClass.process(word);
	}
	private void finishProcess() {
		processingClass.finishProcess();
	}
}
