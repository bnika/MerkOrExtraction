package is.merkor.patternextraction.patterns_to_db;


import java.sql.*;
/**
 * Just for documentation purpose - needs extreme rework!
 * @author anna
 */

public class DBManager {
    
    Connection db;
    Statement sql;          // Statement to run queries with
    DatabaseMetaData dbmd;  // Info the driver delivers about
                            // the DB it just connected to.
    
    public void createTable () 
            throws ClassNotFoundException, SQLException {
       
        String database = "merkorPatternInformation";
        String username = "postgres";
        String password = "";

        try {
            Class.forName("org.postgresql.Driver"); // load the driver
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        db = DriverManager.getConnection("jdbc:postgresql:" + database,
                username, password); // connect to the db
        
        sql = db.createStatement();
        //sql.execute("create table PATTERNS(pattern text, originalString text[])");
        sql.execute("CREATE TABLE lemmaList(lemma text, wordForm text, tags varchar(12))");
        
        sql.close();
        db.close();
        
    }
    
    public void alterTable()
            throws ClassNotFoundException, SQLException {
        String database = "merkorPatternInformation";
        String username = "postgres";
        String password = "SeeKuh";

        try {
            Class.forName("org.postgresql.Driver"); // load the driver
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        db = DriverManager.getConnection("jdbc:postgresql:" + database,
                username, password); // connect to the db
        
        sql = db.createStatement();
        sql.execute("alter table PATTERNS add column number int");
        sql.execute("alter table patterns add column relation text");
        sql.execute("alter table patterns add column note text");
        sql.close();
        db.close();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
        DBManager db = new DBManager();
        db.createTable();
        //db.alterTable();
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            
        } catch (SQLException e) {
            System.err.print("SQLException: ");
            System.err.println(e.getMessage());
        }
    }

}
