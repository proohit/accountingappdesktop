package Application;

import DBConnection.*;
import DBTables.Record;
import DBTables.Table;

import java.util.Hashtable;

public class DBTest {

	public static void main(String[] args) {
		DBManager.createDB("Accounting");
		DBManager.executeStatement("DROP TABLE IF EXISTS Record;");
		Hashtable<String, String> testHashtable = new Hashtable<String, String>();
		testHashtable.put("recordId", "1");
		testHashtable.put("description", "bahn");
		testHashtable.put("value", "bahn");
		// String[] primKeyStrings = {"id"};
		Record recordTable = new Record();
		// DBManager.createTable("Test", cols, colType, "id");
		// DBManager.executeStatement("INSERT INTO Record(id, description, value)
		// VALUES(1,'bahn','-5');");
		// DBManager.executeStatement("INSERT INTO Record(id, description, value)
		// VALUES(2,'einkaufen','-15');");
		// DBManager.executeStatement("INSERT INTO Record(id, description, value)
		// VALUES(3,'essen',-10);");
		recordTable.insertValues(1, "bahn", -5, 2019, 3);
		recordTable.insertValues(2, "einkaufen", -15, 2019, 3);
		DBManager.printTables();
		recordTable.printRows();
	}

}
