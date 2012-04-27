/*******************************************************************************
 * MerkOrExtraction
 * Copyright (c) 2012 Anna B. Nikulásdóttir
 * 
 * License: GNU Lesser General Public License. 
 * See: <http://www.gnu.org/licenses> and <README.markdown>
 * 
 *******************************************************************************/
package is.merkor.cli;

import is.merkor.patternextraction.PatternExtraction;
import is.merkor.patternextraction.PatternInfo;
import is.merkor.patternextraction.PatternMerger;
import is.merkor.preprocessing.IceTagsBinTagsMapping;
import is.merkor.util.FileCommunicatorWriting;
import is.merkor.util.MerkorFile;
import is.merkor.util.MerkorTokenReader;
import is.merkor.util.database.DBPopulation;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

/**
 * A command line interface to the MerkOr Extraction package.
 * 
 * Run java -jar MerkOrExtraction.jar -help to see options available.
 * 
 * More instructions in file README.markdown included in the MerkOrExtraction package.
 * 
 * Usage 1:
 * Create an SQL file containing mappings of ice-nlp tags and BÍN tags for nouns, adjectives
 * and verbs. Filters non-valid words of these classes and writes them out in a text file.
 * 
 * merkorExtractor.jar -bin_mapping -input <inputDir_or_inputFile>
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */
public class Main {
	
	public static List<String> processCommandLine (final CommandLine cmdLine) {
		String input = null;
		String output = null;
		
		List<String> results = new ArrayList<String>();
		
		if (cmdLine.hasOption("help") || cmdLine.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar MerkOrExtraction.jar", MerkorCommandLineOptions.options);
			results.add("no message");
		}
		
		if (cmdLine.hasOption("input")) {
			input = cmdLine.getOptionValue("input");
			
		}
		if (cmdLine.hasOption("output")) {
			output = cmdLine.getOptionValue("output");
		}
		// bin - icenlp tag mapping
		if (cmdLine.hasOption("bin_mapping")) {
			if (null == input)
				results.add("no input given for bin_mapping!");
			else {
				List<String> wordclassList = new ArrayList<String>();
				wordclassList.add("noun");
				wordclassList.add("adjective");
				wordclassList.add("verb");
				long start = System.nanoTime();
				// process the file (or all files in inputDir) for each wordclass
				for (String wc : wordclassList) {
					
					IceTagsBinTagsMapping mapper = new IceTagsBinTagsMapping(wc);
					MerkorTokenReader reader = new MerkorTokenReader(mapper);
					
					File inputDir = new File(input);
					if (inputDir.isDirectory()) {
						File[] files = inputDir.listFiles();
						for(int i = 0; i < files.length; i++) {
							System.out.println("file nr. " + i);
							if(!files[i].getName().startsWith("."))
								reader.readTokensFromFile(files[i].getAbsolutePath());
						}	
					}
					else if (inputDir.isFile()) {
						reader.readTokensFromFile(inputDir.getAbsolutePath());
					}
				}
				double elapsedTime = (double)(System.nanoTime() - start) / 1000000000.0;
				System.out.println("Execution time: " + elapsedTime + " seconds!");
			}
		}
		// db-population
		if (cmdLine.hasOption("fill_db")) {
			String conn = cmdLine.getOptionValue("db_conn");
			String name = cmdLine.getOptionValue("db_name");
			String password = cmdLine.getOptionValue("password");
			
			if (null == conn || null == name || null == password || null == input) {
				results.add("connection, name, password and inputfile needed for option -fill_db!");
				return results;
			}
			long start = System.nanoTime();
			DBPopulation db_pop = new DBPopulation();
			db_pop.populateDBFromFile(conn, name, password, input);
			double elapsedTime = (double)(System.nanoTime() - start) / 1000000000.0;
			System.out.println("Execution time: " + elapsedTime + " seconds!");
		}
		
		if (cmdLine.hasOption("extract_patterns")) {
			PatternExtraction extr = new PatternExtraction();
			List<String> patterns = new ArrayList<String>();
			try {
				File inputDir = new File(input);
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
										patterns = extr.getExtractedPatternsAsStrings();
									}
								}
							}
						}
					}	
				}
				else {
					MerkorFile file = new MerkorFile(input);
					for (String line : file) {
						extr.processLine(line);
						patterns = extr.getExtractedPatternsAsStrings();
					}
					
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			FileCommunicatorWriting.writeListNonAppend(output, patterns);
		}
		if (cmdLine.hasOption("merge_patterns")) {
			String relation = cmdLine.getOptionValue("relation");
			String passwd = cmdLine.getOptionValue("password");
			
			PatternMerger extr = new PatternMerger();
			List<String> patterns = new ArrayList<String>();
			
			patterns = extr.process(relation, passwd);
				
			FileCommunicatorWriting.writeListNonAppend(output, patterns);
		}
		
		return results;
	}
	
	public static void main (String[] args) throws Exception {
		
		List<String> results = new ArrayList<String>();
	
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		
		CommandLineParser parser = new GnuParser();
	    try {
	      
	    	MerkorCommandLineOptions.createOptions();
	    	results = processCommandLine(parser.parse(MerkorCommandLineOptions.options, args));
//	    	out.print("\n");
//	    	for (String str : results) {
//	    		if(!str.equals("no message"))
//	    			out.println(str);
//			}
//	    	out.print("\n");
//			if (results.isEmpty()) {	
//				out.println("nothing found for parameters: ");
//				for (int i = 0; i < args.length; i++)
//					out.println("\t" + args[i]);
//				out.println("for help type: -help or see README.markdown");
//				out.print("\n");
//			}
	    }
	    catch(ParseException e) {
	        System.err.println("Parsing failed.  Reason: " + e.getMessage());
	    }	
	}
}
