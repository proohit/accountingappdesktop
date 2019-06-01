package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import DBConnection.*;
import data.Wallet;

public abstract class Table {
	String tableName;
	LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();

	public void deleteTable() {
		try {
			DBManager.executeStatement("DROP TABLE IF EXISTS " + tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printRows() {
		String sql = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = DBManager.selectStmt(sql);
			System.out.println("Rows of " + tableName);

			List<String> keys = columns.keySet().stream().collect(Collectors.toList());
			for (String key : keys) {
				System.out.print(key + " ");
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

	protected ResultSet getByName(String name) throws SQLException {
		if (!columns.containsKey("name"))
			throw new SQLException("there is no column called " + name + " in table " + tableName + "!");
		String sql = "SELECT * FROM " + tableName + " WHERE name='" + name + "';";
		return DBManager.selectStmt(sql);
	}
}
