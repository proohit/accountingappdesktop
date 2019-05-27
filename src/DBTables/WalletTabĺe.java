package DBTables;

import DBConnection.DBManager;
import DBTables.Table;

public class WalletTabĺe extends Table {
	public WalletTabĺe() {
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
