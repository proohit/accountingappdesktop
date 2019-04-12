package DBConnection;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.sun.org.glassfish.gmbal.Description;
import com.sun.xml.internal.bind.v2.runtime.Name;

import DBTables.Table;
/*
 * Verbindung zwischen Datenbank und Applikation
 * verfügt über Möglichkeiten Queries an die Datenbank zu senden
 */
public class DBManager {
	static String name;

	public static void createDB(String dbName) {
		name= dbName;
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
	private void tryConnection() {
		if(!DBConnection.connected() && name !=null) {
			try {
				DBConnection.connect(name);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
/*
 * Methode zum erstellen einer Tabelle ohne Fremdschlüssel
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
	 * Methode zum Erstellen einer Tabelle mit Primärschlüsseln und Fremdschlüsseln in der geöffneten Datenbank
	 */
	public static void createTable(String tableName, Hashtable<String, String> columns, ArrayList<String> primaryKeys,ArrayList<String> foreignKeys,ArrayList<String> refTables)
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
		sql += "),";
		//hier implementierung von getforeignsql fehlt noch, hard coded!!!!!!!!
			sql+=getForeignSql(foreignKeys, refTables.get(0));
			sql+=");";
		executeStatement(sql);
	}

	// interne methode zum zurückgeben von foreign key + references abschnitt
	private static String getForeignSql(ArrayList<String> foreignKeys, String refTable) {
		String foreignkey = "FOREIGN KEY(";
		String reference = "REFERENCES " + refTable + "(";
		for (int i = 0; i < foreignKeys.size(); i++) {
			foreignkey += foreignKeys.get(i);
			reference+=foreignKeys.get(i);
			if (i < foreignKeys.size() - 1) {
				foreignkey += ", ";
				reference+=", ";
			}
		}
		reference+=") ";
		foreignkey+=") "+reference;
		
		return foreignkey;
	}
	/*
	 * Methode zum Ausführen eines Strings in SQL
	 */
	public static void executeStatement(String sql) {
		try {
			System.out.println("executing query... " + sql);
			System.out.println(DBConnection.newStatement().executeUpdate(sql) + " " + "rows affected.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't execute Query");
		}
	}
	/*
	 * Methode zum Ausführen eines SELECT Strings in SQL
	 */
	public static ResultSet selectStmt(String sql) throws SQLException {
		try {
			return DBConnection.newStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	/*
	 * Methode zum Abgreifen der Namen der Relationen in einer ArrayList
	 */
	public static ArrayList<String> tables() throws SQLException {
		try {
			ResultSet tables = DBConnection.conn.getMetaData().getTables(null, null, "%", null);
			ArrayList<String> tableNames = new ArrayList<String>();
			while (tables.next()) {
				tableNames.add(tables.getString(3));
			}
			return tableNames;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	/*
	 * Methode zum Ausgeben aller Tabellennamen in der geöffneten Datenbank
	 */
	public static void printTables() {
		try {

			ArrayList<String> tables = tables();
			System.out.println("Tables:");
			for (String name : tables) {
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
