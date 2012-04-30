package is.merkor.relationextraction;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import is.merkor.util.FileCommunicatorReading;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XmlCasSerializer;
import org.apache.uima.util.XMLInputSource;
import org.xml.sax.SAXException;

/**
 * A simple application that reads documents from files, sends them though an Analysis Engine, and
 * prints all discovered annotations to files in a directory defined in <code>outputDir</code>.
 * <p>
 * The application takes two arguments:
 * <ol type="1">
 * <li>The path to an XML descriptor for the Analysis Engine to be executed</li>
 * <li>An input directory containing files to be processed</li>
 * </ol>
 * </p>
 * <p>
 * Descriptors used in the Semantic Relations analysis:
 * <ol>
 * <li><code>LemmaDetector.xml</code> Detects lemmata from a POS-Word pair annotated input (included in pipeline)
 * <li><code>PairInOneDetector.xml</code> Annotates Words, POS-tags and Word-POS pairs from IceParser output
 * <li><code>PatternDetector.xml</code> Detects Patterns from phrase annotated input (included in pipeline)
 * <li><code>RelationDetector.xml</code> Detects semantic relations from Word-POS pairs annotated input (included in pipeline)
 * </ol>
 */

public class MerkorEngine implements Runnable {
	private File taeDescriptor; //TextAnalysisEngineDescriptor
	private File inputDir;
	private String outputDir = "processed/";
	private ResourceSpecifier specifier;
	private AnalysisEngine analysisEngine;
	private CAS cas;
	//if using threads:
	private File currentFile;
	
	public MerkorEngine() {
//		// a default constructor
//		// for initialization use: <code>MerkorSimpleEngine(String taeDescriptor, String inputDir)</code>
	}
	/**
	 * Constructor. The <code>taeDescriptor</code> is the name of the <code>TextAnalysisEngineDescriptor</code> 
	 * to use.
	 * 
	 * @param taeDescriptor the TextAnalysisEngineDescriptor to use
	 * @param inputDir the input directory containing the files to process
	 */
	public MerkorEngine (String taeDescriptor, String inputDir) {
		this.taeDescriptor = new File(taeDescriptor);
		this.inputDir = new File(inputDir);
		//if using threads:
		this.currentFile = new File(inputDir);
	}
	/**
	 * Processes the input files according to the TextAnalysisEngineDescriptor
	 */
	public void process() {
		if(!isInitialized()) {
			System.out.println("Couldn't process, MerkorSimpleEngine object is not initalized!");
			return;
		}
		try {
			setResourceSpecifier();
			createAnalysisEngine();
			createCAS();
			//if not using threads:
			//processDirectory();
			//if using threads:
			processFile(currentFile);
			analysisEngine.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isInitialized() {
		//if not using threads:
		//return descriptorExists() && !fileIsDirectory(taeDescriptor, " is a directory!", true)
		//	&& fileIsDirectory(inputDir, " is not a directory!", false);
		//if using threads:
		return descriptorExists();
	}
	private void setResourceSpecifier() 
		throws IOException, InvalidXMLException {
		//get ResourceSpecifier from XML file
		XMLInputSource in = new XMLInputSource(taeDescriptor);
		specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
	}
	private void createAnalysisEngine() throws ResourceInitializationException {
		analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);
	}
	private void createCAS() throws ResourceInitializationException {
		cas = analysisEngine.newCAS();
	}
	private void processDirectory() 
		throws IOException, AnalysisEngineProcessException, SAXException {
		// get all files in the input directory
        File[] files = inputDir.listFiles();
        if (files == null) {
          System.out.println("No files to process");
        } 
        else {
          // process documents
          for (int i = 0; i < files.length; i++) {
        	  cas.reset();
            if (!files[i].isDirectory()) {
            	if(!files[i].getName().startsWith(".")) {
            		processFile(files[i]);
            		System.out.println("processed file nr. " + i);
            		writeAnnotationsForFile(files[i]);
            	}
            }
          }
        }
	}
	
	/**
	   * Processes a single file using this AnalysisEngine,
	   * saving to this CAS
	   * @param aFile file to process
	   *          
	   * 
	   */
	private void processFile(File aFile) throws IOException,
    	AnalysisEngineProcessException, SAXException { //SAXException for thread test
		
		System.out.println("Processing file " + aFile.getName() + " size: " + aFile.length());
		long start = System.currentTimeMillis();
		  // convert file content to String - original
	      String document = FileUtils.file2String(aFile);
	      document = document.trim();
		// put document text in CAS
	      cas.setDocumentText(document);

	      // process
	      analysisEngine.process(cas);
	      writeAnnotationsForFile(aFile);
		  
		  // REFACTORING: split file and work through piece-by-piece
//		try {
//		  BufferedReader in = FileCommunicatorReading.createReader(aFile.getName());
//		  String line = "";
//		  StringBuffer buffer = new StringBuffer(20000);
//		  while ((line = in.readLine()) != null) {
//			  if(buffer.length() < 20000) {
//				  buffer.append(line);
//			  }
//			  else {
//				  buffer.append(line);
//			  
//				  // put document text in CAS
//				  cas.setDocumentText(buffer.toString());
//
//				  // process
//				  analysisEngine.process(cas);
//				  
//				  buffer = new StringBuffer(20000);
//			  }
//			  
//		  }
//		} catch (IOException e) {
//			
//		}
	      
	      long end = System.currentTimeMillis();
   		 System.out.println("total execution time for: " + aFile.getName() + ": " + (end - start));
	}
	/**
	 * Writes the resulting annotations of the process to a file.
	 * If processing large amount of data and not explicitly needing the .xmi output,
	 * skip this method.
	 * @param aFile
	 * @throws SAXException
	 * @throws IOException
	 */
	private void writeAnnotationsForFile(File aFile) throws SAXException, IOException {
	    BufferedOutputStream aStream = new BufferedOutputStream (new FileOutputStream
	    		(outputDir + "annot_"+ aFile.getName() + ".xmi"));
	    XmlCasSerializer.serialize(cas, aStream);
	}

	private boolean descriptorExists() {
		if(!taeDescriptor.exists()) {
			System.out.println(taeDescriptor.getName() + " does not exist!");
			return false;
		} else
			return true;
	}
	private boolean fileIsDirectory(File aFile, String message, boolean printMessageIfTrue) {
		if(aFile.isDirectory()) {
			if(printMessageIfTrue)
				System.out.println(aFile.getName() + message);
			return true;
		} else {
			if(!printMessageIfTrue)
				System.out.println(aFile.getName() + message);
			return false;
		}
	}
	
	public void run() {
		process();
		Thread.yield();
	}
	
  /**
   * Main program.
   * 
   * @param args
   *          Command-line arguments - see class description
   */
  public static void main(String[] args) {
	  long start = System.currentTimeMillis();
	  MerkorEngine engine = new MerkorEngine();
	  boolean validArgs = false;
      // Read and validate command line arguments
      if (args.length == 2) {
    	  //if not using threads:
    	//engine = new MerkorSimpleEngine(args[0], args[1]);
    	  //validArgs = engine.isInitialized();
    	  //if using threads:
    	  File dir = new File(args[1]);
    	  File[] files = dir.listFiles();
          if (files == null) {
            System.out.println("No files to process");
          } 
          else {
            // process documents
        	  ExecutorService exec = Executors.newFixedThreadPool(2);
            for (int i = 0; i < files.length; i++) {
              if (!files[i].isDirectory()) {
              	if(!files[i].getName().startsWith(".")) {
              		//if not using threads:
              		//engine = new MerkorSimpleEngine(args[0], "testData/" + files[i].getName());
              		//if using threads:
              		exec.execute(new MerkorEngine(args[0], args[1] + files[i].getName()));
              		System.out.println("processing file nr. " + i);
              		//if not using threads:
//              		validArgs = engine.isInitialized();
//              		if (!validArgs) {
//              	        printUsageMessage();
//              	      } 
//              	      else {
              	    	 //engine.process(); 
              	     // }
              	}
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("total execution time: " + (end - start));
            exec.shutdown();
          }
      }
      
      
  }
  private static void printUsageMessage() {
    System.err.println("Usage: java net.merkor.engine.MerkorSimpleEngine "
            + "<Analysis Engine descriptor or PEAR file name> <input dir>");
  }
}

  
  
