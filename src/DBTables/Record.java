package DBTables;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;

public class Record extends Table {
	public Record() {
		super();
		tableName = "Record";
		columns.put("recordId", "int");
		columns.put("description", "varchar");
		columns.put("value", "double");
		columns.put("walletName", "varchar");
		columns.put("timestamp", "smalldatetime");
	}
	
	public void insertValues(int id, String description, double value, String walletName) {
		String sql = "INSERT INTO " + tableName + "(recordId, description, value, timestamp, walletName) VALUES(" + id + "," + "'"
				+ description + "'" + "," + value + "," + "strftime('%Y.%m.%d-%H:%M:%S'), '" + walletName + "');";
		DBManager.executeStatement(sql);
		DBManager.executeStatement("UPDATE Wallet SET balance=balance+"+value+" WHERE name=" + "'" + walletName + "';");
	}
	public void createTable() {
		DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Record ("
				+ "recordId int AUTO INCREMENT,"
				+ "description varchar,"
				+ "value double,"
				+ "walletName varchar,"
				+ "timestamp smalldatetime,"
				+ "PRIMARY KEY(recordId),"
				+ "FOREIGN KEY(walletName) REFERENCES Wallet(name));");
	}
}
