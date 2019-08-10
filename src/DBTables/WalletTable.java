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

	public static void createTable() throws SQLException {
		DBManager.executeStatement(
				"CREATE TABLE IF NOT EXISTS Wallet (" + "name varchar," + "balance double," + "PRIMARY KEY(name));");
	}

	public static void insertValue(String name, double balance) throws SQLException {
		DBManager.executeStatement("INSERT INTO Wallet(name, balance) VALUES(" + "'" + name + "'," + balance + ");");
	}

	public static void insertValue(Wallet wallet) throws SQLException {
		String sql = "INSERT INTO Wallet(name, balance) VALUES('" + wallet.getName() + "'," + wallet.getBalance()
				+ ");";
		DBManager.executeStatement(sql);
	}

	public static ArrayList<Wallet> getWallets() {
		try {
			ArrayList<Wallet> result = new ArrayList<Wallet>();
			ResultSet rs = DBManager.selectStmt("SELECT * FROM WALLET");
			while (rs.next()) {
				result.add(new Wallet(rs.getString("name"), rs.getDouble("balance")));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Wallet>();
	}

	public static void setBalance(String walletName, double initial) {
		try {
			DBManager.executeStatement("UPDATE Wallet SET balance=" + initial + " WHERE name='" + walletName + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateBalance(String walletName, double update) throws SQLException {
		DBManager.executeStatement(
				"UPDATE Wallet SET balance=balance+" + update + " WHERE name=" + "'" + walletName + "';");
	}

	public static Wallet getWalletByName(String name) {
		String sql = "SELECT * FROM Wallet WHERE name='" + name + "';";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			rs.next();
			return new Wallet(rs.getString("name"), rs.getDouble("balance"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean contains(String name) throws Exception {
		String sql = "SELECT * FROM Wallet WHERE wallet.name = \" " + name + "\"";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			return rs.next();
		} catch (SQLException e) {
			throw new Exception("invalid Wallet");
		}

	}
}
