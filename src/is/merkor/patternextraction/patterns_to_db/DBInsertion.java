package is.merkor.patternextraction.patterns_to_db;


import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Just for documentation purpose - needs extreme rework!
 * 
 * @author anna
 */
public class DBInsertion {
    
    public Connection db;
    public Statement sql;          // Statement to run queries with
    //DatabaseMetaData dbmd;  // Info the driver delivers about
                            // the DB it just connected to.
    public PreparedStatement insertPatternSt;
    
    public DBInsertion() throws SQLException {
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
        //insertPatternSt = db.prepareStatement("INSERT INTO patterns values('?', {'?'}, 1)");
    }

    public void beginTransaction()
        throws SQLException         {
        sql = db.createStatement();
        //sql.execute ("BEGIN TRANSACTION");
    }

    public void endTransaction()
        throws SQLException         {
        sql = db.createStatement();
        //sql.execute ("END TRANSACTION");
        sql.executeBatch();
    }

    public void insertToDB(List<PatternInfo> patternSequence)
        throws ClassNotFoundException, SQLException {

        
        sql = db.createStatement();
        
        String pattern = "";
        String original = "";
        for (int i = 0; i < patternSequence.size(); i++) {
            String pat = normalize(patternSequence.get(i).getPattern());
            String orig = normalize(patternSequence.get(i).getOriginalString());
            
            //String[] lineArr = lineStr.split(delimiter);
            pattern += pat + " ";
            original += orig + " ";
        }
        pattern = pattern.trim();
        original = original.trim();
        
        try {
            ResultSet results = sql.executeQuery("SELECT * FROM patterns WHERE pattern = '" + pattern + "'");
            String phrases = "";
            if(results != null) {
                if (results.next()) {
                    Array sqlArr = results.getArray("originalstring");
                    String[] javaArr = (String[]) sqlArr.getArray();
                    int arrSize = javaArr.length + 1;
                    StringBuffer concat = new StringBuffer();
                        for(int i = 0; i < javaArr.length; i++) {
                            String tmp = normalize(javaArr[i]);
                            concat.append(tmp);
                            //concat.append(javaArr[i]);
                            concat.append(",");
                        }
                        concat.append(original);
                        phrases = concat.toString();
                        //sql.addBatch("UPDATE patterns SET originalstring  = '{" + phrases + "}', number = " + arrSize + " WHERE pattern = '" + pattern + "'");
                        sql.execute("UPDATE patterns SET originalstring  = '{" + phrases + "}', number = " + arrSize + " WHERE pattern = '" + pattern + "'");
                }
                else {
                sql.execute("INSERT INTO patterns values('" + pattern + "', '{" + original + "}'" + ", 1)");
//                insertPatternSt.setString(1, pattern);
//                insertPatternSt.setString(2, original);
//                insertPatternSt.execute();
                //System.out.println("inserted " + pattern);
                }
                results.close();
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " " + original);
        }
        
        //sql.execute("INSERT INTO patterns values('" + pattern + "', '" + original + "')");
        
    }

    public void insertToLemmaList(String lemma, String wf, String tags) {
        try {
            sql = db.createStatement();
            lemma = normalize(lemma);
            wf = normalize(wf);
            sql.execute("INSERT INTO lemmaList values('" + lemma + "', '" + wf + "', '" + tags + "')");

        } catch (SQLException ex) {
            Logger.getLogger(DBInsertion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void closeConnection() throws SQLException {
        sql.close();
        db.close();
    }
    private String normalize(String line) {
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
