//package Application;
//
//import DBConnection.*;
//import DBTables.RecordTable;
//import DBTables.WalletTable;
//import data.Record;
//import data.Wallet;
//
//public class DBTest {
//
//	public static void main(String[] args) {
//		try {
//			DBManager.createDB("Accounting");
//			WalletTable walletTable = new WalletTable();
////			RecordTable recordTable = new RecordTable();
//
////	    recordTable.deleteTable();
////	    walletTable.deleteTable();
////	    recordTable.createTable();
////	    walletTable.createTable();
////	    walletTable.insertValue("Konto", 50);
////	    walletTable.printRows();
////	    Record rec1 = new Record("essen",-10,"Konto");
////	    Record rec2 = new Record("bahn", -15, "Konto");
////	    Record rec3 = new Record("einkaufen", -5, "Konto");
////	    recordTable.insertValues(rec1);
////	    recordTable.insertValues(rec2);
////	    recordTable.insertValues(rec3);
//			RecordTable.insertValues(new Record("essen", -15, "Bar"));
//			WalletTable.insertValue(new Wallet("Bar", 15));
//			DBManager.debugOn();
////		recordTable.printRows();
//			RecordTable.selectAll().stream().forEach(record -> print(record));
////	    recordTable.getByYear(2019).forEach(DBTest::print);
////	    recordTable.getByMonth(2019,"05").forEach(DBTest::print);
//			RecordTable.getByDescription("essen").stream().filter(record -> record.getYear() == 2019)
//					.forEach(record -> print(record));
//			walletTable.getWalletByName("Konto").stream().forEach(wallet -> print(wallet));
//			walletTable.getWalletByName("Bar").stream().forEach(wallet -> print(wallet));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static <T> void print(T obj) {
//		System.out.println(obj.toString());
//	}
//
//}
