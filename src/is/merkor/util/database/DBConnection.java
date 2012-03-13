package is.merkor.util.database;
	
import java.sql.*;
/**
 * Class DBConnection establishes a connection with a given database
 * and provides a method for disconnecting to that database
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 *
 */
public class DBConnection {
	//String dbConnection = "jdbc:postgresql://macmini.local/";
	String dbConnection = "jdbc:postgresql://localhost:5433/"; // default connection
    String dbName; // name of the database
    String username = "postgres";
    String password = "postgres";
    Connection db;
    
  //Constructor
    public DBConnection (String name, String password) {
        dbName = name;
        try {
            connectToDB(password);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getStackTrace());
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
        }
    }
    //Constructor
    public DBConnection (String connection, String name, String password) {
    	getConnection(connection, password);
        dbName = name;
        try {
            connectToDB(password);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getStackTrace());
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
        }
    }
    
    private void getConnection (String conn, String password) {
    	if (conn.equals("macmini")) {
    		dbConnection = "jdbc:postgresql://macmini.local/";
    		username = "postgres";
    		this.password = "postgres";
    	}
    	else if (conn.equals("local")) {
    		dbConnection = "jdbc:postgresql://localhost:5433/";
    		username = "postgres";
    		this.password = password;
    	}
    	else {
    		System.out.println("No database connection established, please use \"local\" or \"macmini\"");
    		System.exit(-1);
    	}
    }
    
    public void disconnect() {

        if (db != null) {
            try {
                db.close();
                System.out.println("db connection closed\n");
            } catch (SQLException e) {
                System.err.println(e.getStackTrace());
            }
        }
    }
    
    private void connectToDB(String password) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); // load the driver
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
          
        db = DriverManager.getConnection(dbConnection + dbName,
              username, password); // connect to the db
        
        System.out.println("connected to database: " + dbName);

    }
}
