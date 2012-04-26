package is.merkor.util.database;

import is.merkor.patternextraction.PatternInfo;

import java.util.*;
import java.sql.*;

/**
 * A query class for the MerkOr postgresql database.
 * 
 * TODO: needs refactoring to Hibernate!!
 * 
 * @author Anna B. Nikulasdottir
 */

public class DBCommunicator {
	
	    //DBSelection selector = new DBSelection();
		// Set your own db-name and password!
		DBQueryExecutor selector = new DBQueryExecutor("<db-name>", "<password>");
	    List<String> displayedPatterns = new ArrayList<String>();

	    public String getLemmaForWord(String word) {
	        String lemma = "";
	        String q = "SELECT lemma FROM lemmalist WHERE wordform = '" + word + "'";
	        ResultSet res = selector.selectFromDB(q);
	        try {
	            if(res != null) {
	                while (res.next()) {
	                    lemma = res.getString("lemma");
	                    }
	                res.close();
	             }

	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	        if(lemma.equals("")) {

	            String upperWord = word.substring(0,1).toUpperCase() + word.substring(1, word.length());
	            q = "SELECT lemma FROM lemmalist WHERE wordform = '" + upperWord + "'";
	        res = selector.selectFromDB(q);
	        try {
	            if(res != null) {
	                while (res.next()) {
	                    lemma = res.getString("lemma");
	                    }
	                res.close();
	             }

	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	        }

	        return lemma;

	    }
	    public List<PatternInfo> getPatternsToDisplay() {
	        return getPatternsToDisplay("SELECT * FROM patterns where number > 100");
	    }

	    public List<PatternInfo> getPatternsByRelation(String relation) {
	        String query = "SELECT * FROM patterns WHERE relation = '" + relation + "'";
	        return getPatternsToDisplay(query);
	    }

	    public List<PatternInfo> getPatternsAnyRelation() {
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE relation != '" + "null" + "'");
	    }

	    public List<PatternInfo> getPatternsNothingAssigned() {
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE relation = '" + "null" + "'");
	    }

	    public List<PatternInfo> getPatternsByExNumber(int nr) {
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE number > " + nr);
	    }

	    public List<PatternInfo> getPatternsBySearch(String searchString) {
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE pattern LIKE '" + searchString + "'");
	    }

	    public List<PatternInfo> search(String col, String q) {
	        String query = q.replace('*', '%');
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE " + col + " LIKE '" + query + "'");
	    }
	    public List<PatternInfo> getPatternsByStatus(String status) {
	        if(status.equals("no relation"))
	            return getPatternsToDisplay("SELECT * FROM patterns WHERE relation = 'nothing assigned'");
	        if(status.equals("relation"))
	            return getPatternsToDisplay("SELECT * FROM patterns WHERE relation != 'nothing assigned'");
	        if(status.equals("note"))
	            return getPatternsToDisplay("SELECT * FROM patterns WHERE note != ''");
	        return new ArrayList<PatternInfo>();
	    }
	    public List<PatternInfo> getNonValidatedPatterns(int off) {
	        return getPatternsToDisplay("SELECT * FROM patterns WHERE number > 9", 0, off);
	    }
	    public List<PatternInfo> getPatternsToDisplay(String query) {
	        return getPatternsToDisplay(query, 0, 0);
	    }
	    public List<PatternInfo> getPatternsToDisplay(String query, int lim, int off) {
	        //This one needed for verification tool!
	        //String q = query + " ORDER BY number DESC LIMIT 500 OFFSET " + off;
	        String q = query + " ORDER BY number DESC";
	        ResultSet res = selector.selectFromDB(q);
	        //List<String[]> resultList = new ArrayList<String[]>();
	        List<PatternInfo> resultList = new ArrayList<PatternInfo>();
	        String pat = "";
	        
	        StringBuffer text = new StringBuffer();
	        int nr = 0;
	        try {
	            if(res != null) {
	                while (res.next()) {
	                    PatternInfo patInf = new PatternInfo();
	                    pat = res.getString("pattern");
	                    pat = pat.replaceAll("\\[COMMA\\]", ",");
	                    patInf.setPattern(pat);
	                    patInf.setNrOfExamples(res.getInt("number")); // = res.getInt("number");
	                    patInf.setRelation(res.getString("relation"));
	                    patInf.setNote(res.getString("note"));
	                    //System.out.println(pat + " " + nr);
	                    Array sqlArr = res.getArray("originalstring");
	                    String[] javaArr = (String[])sqlArr.getArray();
	                    List<String> phraseList = new ArrayList<String>();
	                    text = new StringBuffer();
	                    for (int i = 0; i < javaArr.length; i++) {
	                        String str = javaArr[i];
	                        str = str.replaceAll("\\[COMMA\\]" , ",");
	                        phraseList.add(str);
//	                        text.append(str);
//	                        text.append("\n");
	                    }
	                    Collections.sort(phraseList);
	                    for (int j = 0; j < phraseList.size(); j++) {
	                        text.append(phraseList.get(j));
	                        text.append("\n");
	                    }
	                    patInf.setOrigString(text.toString());
//	                    String[] result = {pat + " fjöldi: " + nr, text.toString()};
//	                    resultList.add(result);
	                    resultList.add(patInf);
	                    
	                }
	                res.close();
	             }

	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	        
	        
	        return resultList;
	        
	    }
	    public void setNote(PatternInfo patInf) {
	        selector.update(patInf.getPattern(), "note", patInf.getNote());
	    }
	    public void setRelation(PatternInfo patInf) {
	        String normItem = normalizePattern(patInf.getPattern());
	        selector.update(normItem, "relation", patInf.getRelation());
	    }
	    private String normalizePattern(String line) {
	        String res = line;
	        if(line.indexOf('\'') > 0) {
	            res = line.replaceAll("'", "\\\\'");
	        }

	        if(line.lastIndexOf(',') > 0) {
	            res = line.replaceAll(",", "[COMMA]");
	        }

	        return res;
	    }

	}


