package is.merkor.relationextraction;

import is.merkor.util.FileCommunicatorReading;
import is.merkor.util.NamedArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * RelationValidator uses positive and negative lists for given relations to validate
 * extracted relations. 
 * For an incoming relation to be checked:
 * <ol>
 * <li>see if the relation has a positive or negative list
 * <li>if yes, turn the positive flag on/off depending on if the list is pos or neg
 * <li>test the relation words according to the list
 * </ol>
 * 
 * @author Anna B. Nikulásdóttir
 *
 */
public class RelationValidator {
	private ArrayList<NamedArrayList<String>> validationLists;
	private String inputDirectory = "prepositionsMatchFiles";
	
	public RelationValidator() {
		//initialize validationLists by reading files from inputDirectory
		File[] files = new File(inputDirectory).listFiles();
		NamedArrayList<String> matchList;
		
		validationLists = new ArrayList<NamedArrayList<String>>();
       
    	for (int i = 0; i < files.length; i++) {
    		if (!files[i].isDirectory()) {
    			if(!files[i].getName().startsWith(".")) {
    				String filename = files[i].getName();
    				String filePrefix = filename.substring(0, filename.indexOf("."));
    				matchList = new NamedArrayList<String>(filePrefix);
    				matchList.addAll(FileCommunicatorReading.readListFromFile(inputDirectory + "/" + filename));
    				
    				validationLists.add(matchList);
    			}
    		}
    	}
    	
	}
	
	/**
	 * Returns a boolean indicating if the relation described by relationArr
	 * is valid according to this.validationLists.
	 * relationArr must have length == 3, valid content is: 
	 * relationArr[0]: word1 of a relation
	 * relationArr[1]: the name of a relation
	 * relationArr[2]: word2 of a relation
	 * word1 and word2 are tested for validity
	 * @param relationArr
	 * @return
	 * @throws IllegalArgumentException if relationArr.length != 3
	 * XXX : in stead of checking all over the place if relationArr == 3: make an object!!
	 */
	public boolean validateRelation(String[] relationArr) {
		if(relationArr.length != 3)
			throw new IllegalArgumentException("Array length must be == 3");
		
		String relation = relationArr[1];
		for(NamedArrayList list : validationLists) {
			if (list.getName().matches(relation + "_(pos|neg)"))
				return validate(relationArr, list);
		}
		return true;
	}
	
	private boolean validate(String[] relationArr, NamedArrayList<String> list) {
		if (list.getName().matches("\\S+_pos"))
			return validatePositive(relationArr, list);
		else if (list.getName().matches("\\S+_neg"))
			return validateNegative(relationArr, list);
		return true;
	}
	
	private boolean validatePositive(String[] relationArr, NamedArrayList<String> list) {
		boolean found;
		found = findWord(relationArr[0], list);
		if(!found)
			found = findWord(relationArr[2], list);
		return found;
		
	}
	private boolean validateNegative(String[] relationArr, NamedArrayList<String> list) {
		boolean found;
		found = findWord(relationArr[0], list);
		if(!found)
			found = findWord(relationArr[2], list);
		return !found;
	}
	private boolean findWord(String wrd, NamedArrayList<String> list) {
		for(String s : list) {
			s = s.replaceAll("\"", "");
			String[] pair = s.split("\t");
			if(wrd.matches(pair[0]) || wrd.matches(pair[1]))
				return true;
		}
		return false;
	}
}
