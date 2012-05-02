/*******************************************************************************
 * MerkOrCore
 * Copyright (c) 2012 Anna B. Nikulásdóttir
 * 
 * License: GNU Lesser General Public License. 
 * See: <http://www.gnu.org/licenses> and <README.markdown>
 * 
 *******************************************************************************/
package is.merkor.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * Options for the MerkOrCore command line interface.
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */
public class MerkorCommandLineOptions {
	
	private static Option help;
	private static Option help_h;
	private static Option bin_mapping;
	private static Option extract_patterns;
	private static Option merge_patterns;
	private static Option relations2dbstatements;
	
	private static Option input;
	private static Option output;
	private static Option fill_db;
	private static Option db_conn;
	private static Option db_name;
	private static Option password;
	private static Option relation;
	
	
	public static Options options = new Options();
	
	public static void createOptions () {
		
		createBooleanOptions();
		createArgumentOptions();
		
		options.addOption(help);
		options.addOption(help_h);
		options.addOption(bin_mapping);
		options.addOption(input);
		options.addOption(output);
		options.addOption(fill_db);
		options.addOption(extract_patterns);
		options.addOption(merge_patterns);
		options.addOption(relations2dbstatements);
		options.addOption(db_conn);
		options.addOption(db_name);
		options.addOption(password);
		options.addOption(relation);

	}

	private static void createBooleanOptions() {
		help = new Option("help", "print this message");
		help_h = new Option("h", "print this message");
		bin_mapping = new Option("bin_mapping", "call IceTagsBinMapping");
		fill_db = new Option("fill_db", "populate database from file");
		extract_patterns = new Option("extract_patterns", "extract np / pp patterns from file");
		merge_patterns = new Option("merge_patterns", "merge patterns having same relation in the database");
		relations2dbstatements = new Option("relations2dbstatements", "create an .sql file with insert statements for files in relationDetectorResults/");
	}
	private static void createArgumentOptions() {
		input  = OptionBuilder.withArgName("input file or directory")
			.hasArg()
			.withDescription("the input file or directory")
			.create("input");
		output  = OptionBuilder.withArgName("output file or directory")
			.hasArg()
			.withDescription("the output file or directory")
			.create("output");
		db_conn = OptionBuilder.withArgName("db connection to use")
			.hasArg()
			.withDescription("db connection to use (maybe class DBConnection has to be adjusted!)")
			.create("db_conn");
		db_name = OptionBuilder.withArgName("name of the database")
			.hasArg()
			.withDescription("name of the database")
			.create("db_name");
		password = OptionBuilder.withArgName("password")
			.hasArg()
			.withDescription("password - no encryption!")
			.create("password");
		relation = OptionBuilder.withArgName("relation")
		.hasArg()
		.withDescription("relation to look for")
		.create("relation");
		
	}

}

