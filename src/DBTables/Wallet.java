package DBTables;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.Table;

public class Wallet extends Table {
	public Wallet() {
		super();
		columns.put("walletId", "int");
		columns.put("name", "varchar");
		columns.put("balance", "double");
		tableName="Wallet";
		primaryKeys = new ArrayList<String>();
		primaryKeys.add("walletId");
		
		try {
			DBManager.createTable(tableName, columns, primaryKeys);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
