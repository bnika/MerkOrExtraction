package is.merkor.util.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBQueryExecutor {
	private DBConnection dbConnection;
	private Statement sql;
	
	public DBQueryExecutor(String name, String password) {
		dbConnection = new DBConnection(name, password);
		if(dbConnection == null)
			System.out.println("No db connection established!");
		else {
			try {
				sql = dbConnection.db.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public DBQueryExecutor(String connection, String name, String password) {
		dbConnection = new DBConnection(connection, name, password);
		if(dbConnection == null)
			System.out.println("No db connection established!");
		else {
			try {
				sql = dbConnection.db.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void executeUpdateQuery(String query) {
		try {
			sql.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(query);
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		try {
			resultSet = sql.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void closeStatement() throws SQLException {
		sql.close();	
	}

	public void closeConnection() {
		dbConnection.disconnect();
	}

	public ResultSet executeQuery(String query, ArrayList<String> dbArguments) {	
		ResultSet resultSet = null;
		try {
			PreparedStatement prepStatement = dbConnection.db.prepareStatement(query);
			prepStatement.setString(1, dbArguments.get(0));
			prepStatement.setString(2, dbArguments.get(1));
//			for(int i = 1; i <= dbArguments.size(); i++) {
//				prepStatement.setString(i, dbArguments.get(i-1));
//			}
			//resultSet = sql.executeQuery(query);
			resultSet = prepStatement.executeQuery();
			//prepStatement.close();
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void executeUpdateQuery(String query, ArrayList<String> dbArguments) {
		try {
			PreparedStatement prepStatement = dbConnection.db.prepareStatement(query);
			prepStatement.setString(1, dbArguments.get(0));
			prepStatement.setString(2, dbArguments.get(1));
			prepStatement.setString(3, dbArguments.get(2));
//			for(int i = 1; i <= dbArguments.size(); i++) {
//				prepStatement.setString(i, dbArguments.get(i-1));
//			}
			//sql.executeUpdate(query);
			prepStatement.executeUpdate();
			prepStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void executeInsertQuery(String query) {
		try {
			sql.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Code from classes used in the pattern verification tool - should be changed to Hibernate!!
	
	/**
     * Gets all lemmata with ids from the icedict table
     * 
     * @return a List of Associations (lemma=lemmaid)
     */
    public Map select() {
        Map<String, Integer> patMap = new HashMap<String, Integer>();
        ResultSet res = selectFromDB("select * from patterns");
        try {
            if(res != null) {
                if (res.next()) {
                    String pat = res.getString("pattern");
                    if (patMap.containsKey(pat)) {
                        patMap.put(pat, patMap.get(pat) + 1);
                    }
                    else
                        patMap.put(pat, 1);
                }
                res.close();
             }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        return patMap;
        
    }

    public int selectNumber(int nr) {
        int cnt = 0;
        ResultSet res = selectFromDB("select * from patterns where number = " + nr);
        try {
            if(res != null) {
                while (res.next()) {
                    cnt++;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return cnt;
    }

    public void update(String item, String column, String value) {
        
        
        ResultSet res = selectFromDB("select * from patterns where pattern = '" + item + "'");
        try {
            if(res != null) {
                while (res.next()) {
                    System.out.println(item + " found!");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            sql.execute("UPDATE patterns SET relation  = '" + value + "' WHERE pattern = '" + item + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * Gets the row from the database with the lemmaid <c>id</c>
     * 
     * @param id the lemmaid to search for
     * @return a ResultSet with the row including <c>id</c>, <c>null</c> if 
     * selection is not successful
     */
    public ResultSet selectWithID(String id) {
        return selectFromDB("select * from icedict where lemmaid = '" + id + "'");
    }
    public ResultSet selectWithLemma(String lemma) {
        return selectFromDB("select * from icedict where lemma = '" + lemma + "'");
    }
    /**
     * selects rows from the database with <c>statement</c>
     * 
     * @param statement an sql statement to be executed
     * @return the <c>ResultSet</c> resulting from the execution of <c>statement</c>,
     * <c>null</c> if selection is not successful
     */
    public ResultSet selectFromDB(String statement) {
          
        try {
            ResultSet results = sql.executeQuery(statement);
            return results;
            
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return null;
    }

    public ResultSet selectAllWithRelation() {
        return selectFromDB("SELECT * FROM patterns WHERE relation != 'no relation' ORDER BY number DESC");
    }
}
