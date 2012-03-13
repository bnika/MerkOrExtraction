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
	
	private static Option input;
	
	
	public static Options options = new Options();
	
	public static void createOptions () {
		
		createBooleanOptions();
		createArgumentOptions();
		
		options.addOption(help);
		options.addOption(help_h);
		options.addOption(bin_mapping);
		options.addOption(input);

	}

	private static void createBooleanOptions() {
		help = new Option("help", "print this message");
		help_h = new Option("h", "print this message");
		bin_mapping = new Option("bin_mapping", "call IceTagsBinMapping");
	}
	private static void createArgumentOptions() {
		input  = OptionBuilder.withArgName("input file or directory")
			.hasArg()
			.withDescription("the input file or directory")
			.create("input");
	}

}

