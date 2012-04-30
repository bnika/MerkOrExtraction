package is.merkor.util.database;


/**
 * Serves as an intermediate level between an application class and
 * a database class (e.g. <code>DBQueryExecutor</code>). Sends messages to a database
 * class and resolves database special data types like <code>ResultSet</code> and 
 * extracts java <code>String</code>s or <code>Array</code>s
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import is.merkor.util.database.datatypes.LexicalRelation;
import is.merkor.util.database.datatypes.LexicalRelationType;
import is.merkor.util.database.datatypes.LexicalUnit;

public class DBQueryHandler {
	private DBQueryExecutor executor;

	public DBQueryHandler(String dbName) {
		executor = new DBQueryExecutor(dbName, "password");
	}
	public DBQueryHandler(String connection, String dbName) {
		executor = new DBQueryExecutor(connection, dbName);
	}
	/**
	 * Sends the query to DBQueryExecutor and extracts a string from the 
	 * resulting resultSet labeled with columnName
	 * 
	 * @param query SQL query sent to the connected database
	 * @param columnName the name of the column the string should be extracted from
	 * @return the string from the selected column
	 */
	public String getOneStringResult(String query, String columnName) {
		String result = "";
		ResultSet resultSet = executor.executeQuery(query);
		if(null == resultSet)
			return result;
		try {
			while(resultSet.next()) {
				if(columnName.indexOf(',') > 0) {
					String[] colArr = columnName.split(",");
					String col1 = colArr[0];
					String col2 = colArr[1].trim();
					result = resultSet.getString(col1);
					result += "_" + resultSet.getString(col2);
				}
				else
					result = resultSet.getString(columnName);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public void closeDB() {
		try {
			executor.closeStatement();
			executor.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertToDB(ArrayList<String> dbArguments, String table) {
		// TODO Auto-generated method stub
		// 1) check if content of dbArguments is already in the table
		// 2) if not: insert to table
		
		String checkContentQuery = "SELECT * FROM wordforms WHERE wordform = ? AND tags = ?";
		ResultSet results = executor.executeQuery(checkContentQuery, dbArguments);
		
		try {
			
			if(!results.next()) {
				
				String insertStatement = "INSERT INTO wordforms VALUES (?, ?, ?)";
				executor.executeUpdateQuery(insertStatement, dbArguments);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	public String getLemma(String word, String pos, String table) {
		//TODO: prepared statments, lex_unit_table as argument!
		if(word.matches("non_word|sign"))
			return "";
		word = word.replaceAll("'", "");
		String query = "";
		if(table.matches(".+nouns")) {
//			query = "SELECT lemma, bin_id " +
//								"FROM lex_unit_nouns " +
//								"WHERE id IN (SELECT lex_id " +
//								"FROM " + table + 
//								" WHERE wordform = '" + word + "' " +
//								"AND icenlptags = '" + pos + "');";
			query = "SELECT lemma, bin_id " +
					"FROM wordform_lemma_nouns " +
					"WHERE wordform = '" + word + "' " +
					"AND icenlptags = '" + pos + "';";
		}
		else if (table.matches(".+adjs"))
//			query = "SELECT lemma, bin_id " +
//			"FROM lex_unit_adjs " +
//			"WHERE id IN (SELECT lex_id " +
//			"FROM " + table + 
//			" WHERE wordform = '" + word + "' " +
//			"AND icenlptags = '" + pos + "');";
			query = "SELECT lemma, bin_id " +
			"FROM wordform_lemma_adjs " +
			"WHERE wordform = '" + word + "' " +
			"AND icenlptags = '" + pos + "';";
		
		else 
//			query = "SELECT lemma, bin_id " +
//			"FROM lex_unit_verbs " +
//			"WHERE id IN (SELECT lex_id " +
//			"FROM " + table + 
//			" WHERE wordform = '" + word + "' " +
//			"AND icenlptags = '" + pos + "');";
			query = "SELECT lemma, bin_id " +
			"FROM wordform_lemma_verbs " +
			"WHERE wordform = '" + word + "' " +
			"AND icenlptags = '" + pos + "';";
		
			
		return getOneStringResult(query, "lemma, bin_id");
		//fileCommunicator.writeLineAppend("lemmaQueries.sql", query);
		
	}

	public String insertRelation(String word1, String word2, String relation) {
		// TODO:
		//generalize - table as argument
		//derive queryHandlers for classes that use qh, classes that contain db-info like table and column names to use?
		if((word1.indexOf('_') > 0) && (word2.indexOf('_') > 0)) {
			try {
			int word1_id = Integer.parseInt(word1.substring(word1.indexOf('_') + 1));
			int word2_id = Integer.parseInt(word2.substring(word2.indexOf('_') + 1));
			int rel_id = getRelationID(relation);
			int from_id = getLexUnitID(word1_id);
			int to_id = getLexUnitID(word2_id);
			if(rel_id > 0 && from_id > 0 && to_id > 0) {
				String query = "INSERT INTO lex_relations (rel_type, from_lex_unit, to_lex_unit) " +
				"VALUES ("+ rel_id + ", " + from_id + ", " + to_id + ");";
				return query;
				//executor.executeInsertQuery(query);
				//fileCommunicator.writeLineAppend("insertRelations.sql", query);
			}
//			else
//				System.out.println("no database action for: " + word1 + "(id: " + from_id + ")" + word2 + "(id: " + to_id + ") and " + relation);
			} catch (NumberFormatException e) {
				System.out.println("couldn't parseInt for " + word1 + " or " + word2);
				e.printStackTrace();
			}
			
		}
		return "";
//		else
//			System.out.println("no id extracted and no database action for: " + word1 + " " + word2 + " and " + relation);
	}
	private int getLexUnitID(int wordId) {
		int id = 0;
		ResultSet results = executor.executeQuery("SELECT id FROM lex_unit_nouns WHERE bin_id = " + wordId);
		try {
			if(results.next()) {
				id = results.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	private int getRelationID(String relation) {
		int id = 0;
		ResultSet results = executor.executeQuery("SELECT id FROM lex_relation_type WHERE name = '" + relation + "'");
		try {
			if(results.next()) {
				id = results.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void executeUpdate(String query) {
		executor.executeUpdateQuery(query);
		
	}
	/**
	 * Selects the complete content of the nouns lexical unit table in the database
	 * @return an ArrayList of <code>LexicalUnit</code>s, or an empty list if no results are extracted
	 */
	public ArrayList<LexicalUnit> getLexUnitListNouns() {
		ArrayList<LexicalUnit> resultList = new ArrayList<LexicalUnit>();
		ResultSet results = executor.executeQuery("SELECT * FROM lex_unit_nouns");
		try {
			while(results.next()) {
				int id = results.getInt("id");
				int bin_id = results.getInt("bin_id");
				String lemma = results.getString("lemma");
				if(!Character.isUpperCase(lemma.charAt(0))) {
					LexicalUnit lexUnit = new LexicalUnit(id, bin_id, lemma);
					resultList.add(lexUnit);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public String[] getWordsFromSimilarityTable(LexicalUnit unit) {
		ArrayList<String> resultList = new ArrayList<String>();
		
		String lemma = unit.getLemma();
		String searchString = lemma + "_" + unit.getBinId();
		String[] simWords = new String[16];
		ResultSet results = executor.executeQuery("SELECT * FROM top_similarities_2 WHERE lex_item = '" + searchString + "'");
		if(null == results)
			return simWords;
		try {
			while(results.next()) {
				Array arr = results.getArray("similar_items");
				simWords = (String[])arr.getArray();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return simWords;
	}

	private String getLemmaForTest(int id) {
		String lemma = "";
		ResultSet results = executor.executeQuery("SELECT lemma FROM lex_unit_test WHERE id = " + id);
		try {
			if(results.next()) {
				lemma = results.getString("lemma");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lemma;
	}
	public String getLemmaFor(int id) {
		String lemma = "";
		ResultSet results = executor.executeQuery("SELECT lemma FROM lex_unit_nouns WHERE id = " + id);
		try {
			if(results.next()) {
				lemma = results.getString("lemma");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lemma;
	}
	public String getLemmaFor(int id, String table) {
		String lemma = "";
		ResultSet results = executor.executeQuery("SELECT lemma FROM " + table + " WHERE lex_id = " + id);
		try {
			if(results.next()) {
				lemma = results.getString("lemma");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lemma;
	}
	public ArrayList<LexicalRelation> getRelations(int bin_id) {
		ArrayList<LexicalRelation> resultList = new ArrayList<LexicalRelation>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		int id = getIdForBinId(bin_id);
		int id1;
		int id2;
		int type;
		int relId;
		//ResultSet results = executor.executeQuery("SELECT * FROM distinct_relations WHERE from_lex_unit = " + id + " OR to_lex_unit = " + id);
		ResultSet results = executor.executeQuery("SELECT * FROM lex_relations_new WHERE (from_bin_id = " + bin_id + " OR to_bin_id = " + bin_id + ") and score > 1");
		try {
			while(results.next()) {
				boolean shouldAdd = true;
				//relId = results.getInt("id");
				relId = 1;
				type = results.getInt("rel_type");
				//id1 = results.getInt("from_lex_unit");
				//id2 = results.getInt("to_lex_unit");
				id1 = results.getInt("from_bin_id");
				id2 = results.getInt("to_bin_id");
				LexicalRelation relation = new LexicalRelation(relId, type, id1, id2);
				for(LexicalRelation rel : resultList) {
					if(rel.equals(relation))
						shouldAdd = false;
				}
				if(shouldAdd)
					resultList.add(relation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	private int getIdForBinId(int bin_id) {
		ResultSet results = executor.executeQuery("SELECT id FROM lex_unit_nouns WHERE bin_id = " + bin_id);
		try {
			if(results.next()) {
				return results.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<LexicalRelationType> initializeRelationsList() {
		ArrayList<LexicalRelationType> relationsList = new ArrayList<LexicalRelationType>();
		ResultSet results = executor.executeQuery("SELECT * FROM lex_relation_type");
		try {
			while(results.next()) {
				int id = results.getInt(1);
				String name = results.getString(2);
				String inv = results.getString(3);
				String wordcl = results.getString(4);
				boolean trans = results.getBoolean(5);
				LexicalRelationType type = new LexicalRelationType(id, name, inv, wordcl, trans);
				relationsList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return relationsList;
		
	}

	public String getClusterFor(String combinedLemma) {
		int id = 0;
		StringBuffer clusterBuffer = new StringBuffer();
		ResultSet results = executor.executeQuery("SELECT cluster_id FROM cluster_items_2 WHERE lex_item = '" + combinedLemma + "'");
		try {
			if(results.next()) {
				id = results.getInt("cluster_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(id > 0) {
			String[] clusterArr;
			results = executor.executeQuery("SELECT name, cluster FROM clusters_2 WHERE id = " + id);
			try {
				if(results.next()) {
					String name = results.getString("name");
					Array arr = results.getArray("cluster");
					clusterArr = (String[])arr.getArray();
					clusterBuffer.append(name + "_");
					for(int i = 0; i < clusterArr.length; i++) {
						//highlight the lemma in the cluster
						if(clusterArr[i].equals(combinedLemma))
							clusterBuffer.append("<span style=\"background-color: #FFFF00\">" + clusterArr[i].substring(0, clusterArr[i].indexOf("_")) + "</span>, ");
						else
							clusterBuffer.append(clusterArr[i].substring(0, clusterArr[i].indexOf("_")) + ", ");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clusterBuffer.toString();
	}

	public HashMap<String, HashMap<String, Double>> getTopSimilarities(ArrayList<String> lemmata) {
		HashMap<String, HashMap<String, Double>> similaritiesMap = new HashMap<String, HashMap<String, Double>>();
		
		String[] simItemsArr;
		Double[] simValuesArr;
		String lemma;
		//ResultSet results = executor.executeQuery("SELECT lex_item, similar_items, similarity_values FROM top_similarities_ww3");
		ResultSet results = executor.executeQuery("SELECT lex_item, similar_items, similarity_values FROM similarities_vo_oldFormat");
		try {
			while(results.next()) {
				HashMap<String, Double> tmpMap = new HashMap<String, Double>();
				lemma = results.getString("lex_item");
				if(lemmata.contains(lemma)) {
					Array itemsArr = results.getArray("similar_items");
					Array valuesArr = results.getArray("similarity_values");
					
					simItemsArr = (String[])itemsArr.getArray();
					simValuesArr = (Double[])valuesArr.getArray();
					for (int i = 0; i < simItemsArr.length; i++) {
						tmpMap.put(simItemsArr[i], simValuesArr[i]);
					}
					similaritiesMap.put(lemma, tmpMap);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return similaritiesMap;
	}

	public boolean isNoun(String s) {
		ArrayList<String> gender = new ArrayList<String>();
		gender.add("kk");
		gender.add("hk");
		gender.add("kvk");
		String lemma = s.substring(0, s.indexOf("_"));
		int id = Integer.parseInt(s.substring(s.indexOf("_") + 1, s.length()));
		ResultSet results = executor.executeQuery("SELECT ordflokkur FROM bin WHERE uppflettiord = '" + lemma + "'" +
				" AND id = " + id);
		try {
			while(results.next()) {
				if(gender.contains(results.getString("ordflokkur")))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getLexId(String lemma, Integer bin_id) {
		int id = 0;
		ResultSet results = executor.executeQuery("SELECT lex_id FROM lexical_items WHERE lemma = '" + lemma + "' " +
				"AND bin_id = " + bin_id);
		try {
			while(results.next()) {
				id = results.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getWordClassFor(Integer id) {
		String wordclass = "";
		ResultSet results = executor.executeQuery("SELECT ordflokkur FROM bin WHERE id = " + id);
		try {
			while (results.next()) {
				wordclass = results.getString("ordflokkur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (wordclass.equals("kvk") || wordclass.equals("hk") || wordclass.equals("kk"))
			return "noun";
		if (wordclass.equals("lo"))
			return "adjective";
		if (wordclass.equals("so"))
			return "verb";
		return "";
	}

	public String getWordClassFor(String lemma, Integer id) {
		String wordclass = "";
		ResultSet results = executor.executeQuery("SELECT ordflokkur FROM bin WHERE id = " + id + " AND uppflettiord = '" + lemma + "'");
		try {
			while (results.next()) {
				wordclass = results.getString("ordflokkur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (wordclass.equals("kvk") || wordclass.equals("hk") || wordclass.equals("kk"))
			return "noun";
		if (wordclass.equals("lo"))
			return "adjective";
		if (wordclass.equals("so"))
			return "verb";
		return "";
	}
	public ArrayList<Integer> getBinId (String string, String wordclass) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String wc = getWordclass(wordclass);
		String statement = "";
		if (wc.equals("hk"))
			statement = "SELECT id FROM bin WHERE uppflettiord = '" + string + "' AND " + getNounStatement();
		else
			statement = "SELECT id FROM bin WHERE uppflettiord = '" + string + "' AND ordflokkur = '" + wc + "'";
		ResultSet results = executor.executeQuery(statement);
		try {
			while (results.next()) {
				ids.add(results.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	public ArrayList<Integer> getBinIdMixed(String relWord, String wordclass) {
ArrayList<Integer> ids = new ArrayList<Integer>();
		String wc = getWordclass(wordclass);
		String statement = "";
		if (wc.equals("kk"))
			statement = "SELECT id FROM bin WHERE beygingarmynd = '" + relWord + "' AND " + getNounStatement();
		else
			statement = "SELECT id FROM bin WHERE beygingarmynd = '" + relWord + "' AND ordflokkur = '" + wc + "'";
		ResultSet results = executor.executeQuery(statement);
		try {
			while (results.next()) {
				ids.add(results.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	private String getWordclass (String str) {
		if (str.equals("adjective"))
			return "lo";
		if (str.equals("verb"))
			return "so";
		else
			return "hk";
	}
	private String getNounStatement () {
		return "(ordflokkur = 'kk' OR ordflokkur = 'kvk' OR ordflokkur = 'hk')";  
	}

}
