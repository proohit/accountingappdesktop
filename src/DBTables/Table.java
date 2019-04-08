package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import DBConnection.*;

public abstract class Table {
	String tableName;
	ArrayList<String> primaryKeys;
	ArrayList<String> foreignKeys;
	Hashtable<String, String> columns = new Hashtable<String, String>();
	ArrayList<String> refTables;

	// only for empty constructor
	public Table() {

	}

	/*
	 * public void insertValues(Hashtable<String, String> values) { String sql =
	 * "INSERT INTO " + tableName + "("; Iterator<String> iterator =
	 * columns.keySet().iterator(); int i=1; while(iterator.hasNext()) {
	 * sql+=iterator.next().toString(); if(i <columns.size()) { sql+=", "; } } i=1;
	 * sql+=") VALUES("; Iterator<String> iterator2=values.keySet().iterator();
	 * while(iterator2.hasNext()) { sql+=iterator2.next().toString(); if(i
	 * <columns.size()) { sql+=", "; } } sql+=");"; DBManager.executeStatement(sql);
	 * }
	 */

	public void printRows() {
		String sql = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			System.out.println("Rows of " + tableName);
			while (rs.next()) {
				Iterator<String> iterator = columns.keySet().iterator();
				while (iterator.hasNext()) {
					System.out.print(rs.getObject(iterator.next().toString()) + " ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
