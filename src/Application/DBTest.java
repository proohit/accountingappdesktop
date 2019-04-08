package Application;

import DBConnection.*;
import DBTables.Record;
import DBTables.Table;
import DBTables.Wallet;

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
		Wallet walletTable = new Wallet();
		Record recordTable=new Record();
//		DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Record(recordId int, description varchar, value double, walletId int, PRIMARY KEY(recordId), FOREIGN KEY(walletId) REFERENCES Wallet(walletId));");
//		Record recordTable = new Record();
//		
		recordTable.insertValues(1, "bahn", -5, 2019, 3,0);
		recordTable.insertValues(2, "einkaufen", -15, 2019, 3,0);
		DBManager.printTables();
		recordTable.printRows();
	}

}
