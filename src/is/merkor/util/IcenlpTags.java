package is.merkor.util;

/**
 * A data structure containing regular expressions representing all
 * IceNLP tags.
 * Contains a method validating if a string is an IceNLP tag.
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */
public class IcenlpTags {
	
	public static final String[] TAG_REGEX = {
		"n[kvhx][ef][noþe]g?",
		"n[kvhx][ef][noþe][-g][mös]",
		"l[kvh][ef][noþe][svo][fme]",
		"f[abeopst][kvh12][ef][noþe]",
		"g[kvh][ef][noþe]",
		"tf[kvh12][ef][noþe]",
		"t[aop]",
		"s[nbfvsl][gm][123][ef][nþ]",
		"sþ[gm][kvh][ef][no]",
		"s[lns][gm]",
		"a[me]?[auoþe]",
		"c[nt]?",
		"e",
		"x"	
	};
	
	public static boolean isTag (String str) {
		for (String tag : TAG_REGEX) {
			if(str.matches(tag))
				return true;
		}
		return false;
	}
}
