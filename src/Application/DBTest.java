package Application;

import DBConnection.*;
import DBTables.Record;
import DBTables.Table;
import DBTables.Wallet;

import java.util.Hashtable;

public class DBTest {

	public static void main(String[] args) {
		DBManager.createDB("Accounting");
		Wallet walletTable = new Wallet();
		Record recordTable=new Record();
		recordTable.deleteTable();
		walletTable.deleteTable();
		recordTable.createTable();
		walletTable.createTable();
		walletTable.insertValue("Konto", 50);
		walletTable.printRows();
		recordTable.insertValues(1, "bahn", -5, "Konto");
		recordTable.insertValues(2, "einkaufen", -15, "Konto");
		DBManager.debugOn();
		DBManager.printTables();
		recordTable.printRows();
		walletTable.printRows();
	}

}
