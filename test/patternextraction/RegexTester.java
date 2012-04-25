package patternextraction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
	
	public static void main (String[] args) {
		
//		String regex = "(\\[CP.+|,.*)|(\\[PP\\s+([\\S]+\\s+){2}PP\\])|(\\[NP\\s+[\\S]+\\s+[^n]+\\s+NP\\])";
//		
//		String test1 = "[NP 4,6 ta NP]";
//		String test2 = "[PP í aþ PP]";
//		String test3 = "[NP hús nsdif NP]";
//		String test4 = "[PP í aþ fleiri orð PP]";
//		
//		System.out.println(test1.matches(regex));
//		System.out.println(test2.matches(regex));
//		System.out.println(test3.matches(regex));
//		System.out.println(test4.matches(regex));
		
		String line1 = "Email: ashishg @ stanford.edu<o:p></o:p><br>";
		String line2 = "Admin asst: Roz Morf, Terman 405, (650)723-4173, rozm @ stanford.edu</span>";
		String line3 = "<dt>Professor David Cheriton <A href=\"mailto:cheriton@cs.stanford.edu\">david.cheriton at cs.stanford.EDU</A>"; // two mathces - check for identical matches!
		String line4 = "d-l-w-h-@-s-t-a-n-f-o-r-d-.-e-d-u";
		String line5 = "engler WHERE stanford DOM edu";
		String line6 = "<li>Fax: (650) 725-4671 </li>";
		String line7 = "<script> obfuscate('stanford.edu','jurafsky'); </script>";
		String line8 = "\"mailto:social.media@pppl.gov?subject=Web site suggestions and feedback\"";
		
		//email patterns:
		Pattern pat1 = Pattern.compile("([\\w-]+)\\s*(?:@|at)\\s*((?:[\\w-]+(\\.)){1,3}[\\w-]{3,6})");
		Pattern pat = Pattern.compile("((?:[\\w-]+(?:\\.)?){1,2}(?:[\\w-]+))\\s*(?:@| at )\\s*((?:[\\w-]+(\\.)){1,3}[\\w-]{3,6})");
		Pattern pat2 = Pattern.compile("([\\w]+)\\s*WHERE\\s*([\\w]+)\\s+DOM\\s+([\\w]{3})");
		Pattern pat3 = Pattern.compile("'([\\w]+\\.[\\w]+)',\\s*'([\\w]+)'");
		
		Pattern pat4 = Pattern.compile("mailto:((?:[\\w-_]+(?:\\.)?){1,2}(?:[\\w-_]+))@((?:[\\w]+(\\.)){1,3}[\\w]{3})");
		Pattern telPat = Pattern.compile("\\(?650\\)?\\s+([0-9]{3})-([0-9]{4})");
	
		
		 Matcher m = pat4.matcher(line8);
	        while(m.find()) {
	          String prefix = m.group(1).toLowerCase().replaceAll("-", "");
	          String affix = m.group(2).toLowerCase().replaceAll("-", "");
	          String email = "email: " + prefix + "@" + affix;
	          System.out.println(email);
	          
	        }
	        m = pat3.matcher(line7);
	        while(m.find()) {
	          String prefix = m.group(2).toLowerCase();
	          String affix = m.group(1).toLowerCase();
	          String email = "email: " + prefix + "@" + affix;
	          System.out.println(email);
	          
	        }
	        m = telPat.matcher(line6);
	        while(m.find()) {
		         
		          String email = "tel: 650-" + m.group(1) + "-" + m.group(2);
		          System.out.println(email);
		          
		        }
	        
		
		
	}
}
