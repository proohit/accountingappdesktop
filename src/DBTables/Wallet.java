package DBTables;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.Table;

public class Wallet extends Table {
	public Wallet() {
		super();
		tableName = "Wallet";
		columns.put("name", "varchar");
		columns.put("balance", "double");
	}
	public void createTable() {
		DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Wallet ("
				+ "name varchar,"
				+ "balance double,"
				+ "PRIMARY KEY(name));");
	}
	public void insertValue(String name, double balance) {
		DBManager.executeStatement("INSERT INTO Wallet(name, balance) VALUES(" + "'" + name + "'," + balance + ");");
	}
}
