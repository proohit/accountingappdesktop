package Application;

import DBConnection.*;
import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;

public class DBTest {

    public static void main(String[] args) {
	try {
	    DBManager.createDB("Accounting");
	    WalletTable walletTable = new WalletTable();
	    RecordTable recordTable = new RecordTable();

//	    recordTable.deleteTable();
//	    walletTable.deleteTable();
//	    recordTable.createTable();
//	    walletTable.createTable();
//	    walletTable.insertValue("Konto", 50);
//	    walletTable.printRows();
//	    Record rec1 = new Record("essen",-10,"Konto");
//	    Record rec2 = new Record("bahn", -15, "Konto");
//	    Record rec3 = new Record("einkaufenm", -5, "Konto");
//	    recordTable.insertValues(rec1);
//	    recordTable.insertValues(rec2);
//	    recordTable.insertValues(rec3);
	    
	    DBManager.debugOn();
	    DBManager.printTables();
//		recordTable.printRows();
	    recordTable.selectAll().forEach(DBTest::print);
//	    recordTable.getByYear(2019).forEach(DBTest::print);
//	    recordTable.getByMonth(2019,"05").forEach(DBTest::print);
	    recordTable.getByDescription("einkaufen").stream().filter(record->record.getYear()==2019).forEach(DBTest::print);
	    walletTable.printRows();
	} catch (Exception e) {
	    e.printStackTrace();
	    // TODO: handle exception
	}
    }

    private static void print(Record rec) {
	System.out.println(rec.toString());
    }

}
