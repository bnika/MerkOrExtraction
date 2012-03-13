package is.merkor.util.database;

import java.io.BufferedReader;
import java.io.IOException;

import is.merkor.util.FileCommunicatorReading;

/**
 * This class takes a file containing sql insert statements as input
 * and populates the given database table with this content
 * 
 * The parameter needed are:
 * 1) The database connection, 'local' or 'macmini'
 * 2) The name of the database
 * 3) The name of the sql-file
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 */
public class DBPopulation {

	public DBPopulation() {
		
	}
	
	public void populateDBFromFile(String connection, String db, String password, String filename) {
		DBQueryExecutor insertion = new DBQueryExecutor(connection, db, password);
		int counter = 0;
		try {
			BufferedReader in = FileCommunicatorReading.createReader(filename);
			String line = "";
			while((line = in.readLine()) != null) {
				insertion.executeUpdateQuery(line);
				counter++;
				if(counter%10000 == 0)
					System.out.print("\rlines: " + counter);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n");
	}
}
