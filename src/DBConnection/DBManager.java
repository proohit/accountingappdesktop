package DBConnection;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
	String name;

	public static void createDB(String dbName) {

		if (!DBConnection.connected()) {
			try {
				DBConnection.connect(dbName);
				DatabaseMetaData meta = DBConnection.conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't create DB.");
			}
		}
	}

	public static void createTable(String tableName, String cols[], String colType[], String primKey) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n";
		if (cols.length != colType.length) {
			System.out.println("ERROR: column array doesnt match column type array");
			return;
		} else {
			for (int i = 0; i < cols.length; i++) {
				sql += cols[i] + " " + colType[i] + ",\n";
			}
			sql += "PRIMARY KEY(" + primKey + "));";
		}
		executeStatement(sql);
	}
	//creates an empty table with tableName
	public static void createTable(String tableName) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName;
	}

	public static void executeStatement(String sql) {
		try {
			System.out.println("executing query... " + sql);
			System.out.println(DBConnection.newStatement().executeUpdate(sql) + " " + "rows affected.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't execute Query");
		}
	}

	public static ResultSet selectStmt(String sql) throws SQLException {
		try {
			return DBConnection.newStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public static ArrayList<String> tables() throws SQLException {
		try {
			ResultSet tables = DBConnection.conn.getMetaData().getTables(null, null, "%", null);
			ArrayList<String> tableNames = new ArrayList<String>();
			for (int i = 0; tables.next(); i++) {
				tableNames.add(tables.getString(3));
			}
			return tableNames;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public static void printTables() {
		try {
			ArrayList<String> tables = tables();
			for (String name : tables) {
				System.out.print(name + " ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void printTable(String tableName) {
		String sql = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = selectStmt(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
