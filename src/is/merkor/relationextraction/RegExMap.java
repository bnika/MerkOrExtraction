package is.merkor.relationextraction;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import is.merkor.util.FileCommunicatorReading;

public class RegExMap extends HashMap {
	
	public RegExMap(Map<String, String> aMap) {
		
		convertKeysToPatterns(aMap);
	}
	
	public RegExMap(Map<String, String> aMap, int nr) {
		convertKeysToPatterns(aMap, nr);
	}
	public RegExMap(String filename) {
		Map<String, String> map = FileCommunicatorReading.getStringStringMapFromFile(filename);
		convertKeysToPatterns(map);
	}

	public String getRegExMatch(String aStringToMatch) {
		String result = "";
		
		Set<Pattern> keys = this.keySet();
		
		//try to match aStringToMatch to a key
		for(Pattern p : keys) {
			Matcher match = p.matcher(aStringToMatch);
			if(match.matches()) {
				result = (String)get(p);
				break;
			}	
		}	
		return result;
	}
	private void convertKeysToPatterns(Map aMap) {
		Set<String> keys = aMap.keySet();
		
		//convert all keys to a pattern
		//fill this map with pattern keys and associated values
		for(String s : keys) {
			Pattern pat = Pattern.compile(s);
			String val = (String)aMap.get(s);
			this.put((Pattern)pat, (String)val);
		}
	}
	private void convertKeysToPatterns(Map aMap, int nr) {
		Set<String> keys = aMap.keySet();
		
		//convert all keys to a pattern
		//fill this map with pattern keys and nr
		for(String s : keys) {
			Pattern pat = Pattern.compile(s);
			this.put((Pattern)pat, nr);
		}
	}
}
