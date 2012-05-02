package is.merkor.cli;

import is.merkor.relationextraction.MerkorEngine;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainAnnotators {
	
	// Process using two threads - needs debugging for filename
	// the directory included in filename here, problems in reading and writing in the Engine class.
//	public static void processWithThreads (String[] args) {
//		File dir = new File(args[1]);
//  	  	File[] files = dir.listFiles();
//        if (files == null) {
//        	System.out.println("No files to process");
//        } 
//        else {
//        	ExecutorService exec = Executors.newFixedThreadPool(2);
//        	for (int i = 0; i < files.length; i++) {
//        		if (!files[i].isDirectory()) {
//        			if(!files[i].getName().startsWith(".")) {
//        				exec.execute(new MerkorEngine(args[0], args[1] + files[i].getName())); // why doesn't the thread version write an .xmi file?
//        				System.out.println("processing file nr. " + i);
//        			}
//        		}
//        	}
//        	exec.shutdown();
//        }
//	}
	
	public static void processNoThreads (String [] args) {
		MerkorEngine engine = new MerkorEngine(args[0], args[1]);
		if (engine.isInitialized())
			engine.process();
	}
	
	public static void main (String[] args) {
	    long start = System.currentTimeMillis();
	    if (args.length == 2) {
	    	processNoThreads(args);
	    	long end = System.currentTimeMillis();
	    	System.out.println("total execution time: " + (end - start));
	    }
	    else
    	  printUsageMessage();
	}
	private static void printUsageMessage() {
	    System.err.println("Usage: java net.merkor.engine.MerkorSimpleEngine "
	            + "<Analysis Engine descriptor or PEAR file name> <input dir>");
	}
}
