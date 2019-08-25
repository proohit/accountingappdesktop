package DBConnection;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/*
 * Verbindung zwischen Datenbank und Applikation
 * verfuegt ueber Moeglichkeiten Queries an die Datenbank zu senden
 */
public class DBManager {
	static String name;
	static boolean debug = false;

	public static void debugOn() {
		debug = true;
	}

	public static void debugOff() {
		debug = false;
	}

	public static void createDB(String dbName) {
		name = dbName;
			try {
				DBConnection.connect(dbName);
				DatabaseMetaData meta = DBConnection.conn.getMetaData();
				log("The driver name is " + meta.getDriverName());

			} catch (SQLException e) {
				e.printStackTrace();
				log("Couldn't create DB.");
			}
	}

	/*
	 * Methode zum erstellen einer Tabelle ohne Fremdschluessel
	 */
	public static void createTable(String tableName, Hashtable<String, String> columns, ArrayList<String> primaryKeys)
			throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
		Iterator<String> iterator = columns.keySet().iterator();
		while (iterator.hasNext()) {
			Object nextObject = iterator.next();
			sql += nextObject.toString() + " " + columns.get(nextObject) + ", ";
		}
		sql += "PRIMARY KEY(";
		for (int i = 0; i < primaryKeys.size(); i++) {
			sql += primaryKeys.get(i);
			if (i < primaryKeys.size() - 1)
				sql += ", ";
		}
		sql += "));";
		executeStatement(sql);
	}

	/*
	 * Methode zum Ausfuehren eines Strings in SQL
	 */
	public static void executeStatement(String sql) throws SQLException {
			log("executing query... " + sql);
			log(DBConnection.newStatement().executeUpdate(sql) + " " + "rows affected.");
	}

	/*
	 * Methode zum Ausfuehren eines SELECT Strings in SQL
	 */
	public static ResultSet selectStmt(String sql) throws SQLException {
		return DBConnection.newStatement().executeQuery(sql);
	}

	/*
	 * Methode zum Abgreifen der Namen der Relationen in einer ArrayList
	 */
	public static ArrayList<String> tables() throws SQLException {
		ResultSet tables = DBConnection.conn.getMetaData().getTables(null, null, "%", null);
		ArrayList<String> tableNames = new ArrayList<String>();
		while (tables.next()) {
			tableNames.add(tables.getString(3));
		}
		return tableNames;
	}

	/*
	 * Methode zum Ausgeben aller Tabellennamen in der geoeffneten Datenbank
	 */
	public static void printTables() {
		try {

			ArrayList<String> tables = tables();
			log("Tables:");
			for (String name : tables) {
				log(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void log(String text) {
		if (debug)
			System.out.println(text);
	}

}
