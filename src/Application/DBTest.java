package Application;

import java.sql.Date;
import java.util.Calendar;

import DBConnection.*;
import DBTables.RecordTable;
import DBTables.WalletTabĺe;
import data.Record;

public class DBTest {

    public static void main(String[] args) {
	try {
	    DBManager.createDB("Accounting");
	    WalletTabĺe walletTable = new WalletTabĺe();
	    RecordTable recordTable = new RecordTable();

	    recordTable.deleteTable();
	    walletTable.deleteTable();
	    recordTable.createTable();
	    walletTable.createTable();
	    walletTable.insertValue("Konto", 50);
	    walletTable.printRows();
	    Record rec1 = new Record(Calendar.getInstance().getTime().toString(),3,"essen",-10,"Konto");
recordTable.insertValues(rec1);
	    recordTable.insertValues(1, "bahn", -5, "Konto");
	    recordTable.insertValues(2, "einkaufen", -15, "Konto");
	    DBManager.debugOn();
	    DBManager.printTables();
//		recordTable.printRows();
	    recordTable.selectAll().forEach(DBTest::print);
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
