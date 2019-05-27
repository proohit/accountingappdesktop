package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import DBConnection.*;

public abstract class Table {
	String tableName;
	ArrayList<String> primaryKeys;
	ArrayList<String> foreignKeys;
	LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
	ArrayList<String> refTables;

	// only for empty constructor
	public Table() {

	}
	public void deleteTable() {
		DBManager.executeStatement("DROP TABLE IF EXISTS " + tableName);
	}
	public void printRows() {
		String sql = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			System.out.println("Rows of " + tableName);
			
			List<String> keys = columns.keySet().stream().collect(Collectors.toList());
			for(String key:keys) {
				System.out.print(key+ " ");
			}
			System.out.println();
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
