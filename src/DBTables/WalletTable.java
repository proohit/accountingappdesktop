package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.Table;
import data.Wallet;

public class WalletTable extends Table {
	public WalletTable() {
		super();
		tableName = "Wallet";
		columns.put("name", "varchar");
		columns.put("balance", "double");
	}

	public void createTable() throws SQLException {
		DBManager.executeStatement(
				"CREATE TABLE IF NOT EXISTS Wallet (" + "name varchar," + "balance double," + "PRIMARY KEY(name));");
	}

	public void insertValue(String name, double balance) throws SQLException {
		DBManager.executeStatement("INSERT INTO Wallet(name, balance) VALUES(" + "'" + name + "'," + balance + ");");
	}

	public void insertValue(Wallet wallet) throws SQLException {
		String sql = "INSERT INTO Wallet(name, balance) VALUES('" + wallet.getName() + "'," + wallet.getBalance()
				+ ");";
		DBManager.executeStatement(sql);
	}
	public ArrayList<Wallet> getWalletByName(String name) throws SQLException {
		ArrayList<Wallet> result = new ArrayList<Wallet>();
		ResultSet rs = getByName(name);
		while (rs.next()) {
			result.add(new Wallet(rs.getString("name"), rs.getDouble("balance")));
		}
		return result;
	}
	public ArrayList<Wallet> getWallets() throws SQLException{
		ArrayList<Wallet> result = new ArrayList<Wallet>();
		ResultSet rs = DBManager.selectStmt("SELECT * FROM WALLET");
		while (rs.next()) {
			result.add(new Wallet(rs.getString("name"), rs.getDouble("balance")));
		}
		return result;
	}
	public void updateBalance(String walletName, double update) throws SQLException  {
			DBManager.executeStatement(
					"UPDATE Wallet SET balance=balance+" + update + " WHERE name=" + "'" + walletName + "';");
	}
}
