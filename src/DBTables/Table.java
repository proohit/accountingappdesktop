package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;

import DBConnection.*;

public class Table {
	String tableName;
	String[] primaryKeys;
	Hashtable<String, String> columns = new Hashtable<String, String>();

	public Table(String tableName, Hashtable<String, String> columns, String[] primaryKeys) {
		try {
			DBManager.createTable(tableName, columns, primaryKeys);
			this.tableName=tableName;
			this.primaryKeys = primaryKeys;
			this.columns = columns;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void printRows() {
		String sql = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			System.out.println("Rows of " + tableName);
			while(rs.next()) {
				Iterator<String> iterator = columns.keySet().iterator();
				while(iterator.hasNext()) {
					System.out.print(rs.getObject(iterator.next().toString())+" ");	
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
