package is.merkor.util.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
