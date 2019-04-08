package DBTables;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;

public class Record extends Table {
	public Record() {
		super();
		columns.put("recordId", "int");
		columns.put("description", "varchar");
		columns.put("value", "double");
		columns.put("year", "int");
		columns.put("month", "int");
		tableName = "Record";
		primaryKeys = new ArrayList<String>();
		primaryKeys.add("recordId");
		foreignKeys = new ArrayList<String>();
		foreignKeys.add("year");
		foreignKeys.add("month");
		refTables = new ArrayList<String>();
		refTables.add("Wallet");
		try {
			DBManager.createTable("Record", columns, primaryKeys, foreignKeys, refTables);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertValues(int id, String description, double value, int year, int month) {
		String sql = "INSERT INTO " + tableName + "(recordId, description, value, year, month) VALUES(" + id + "," + "'"
				+ description + "'" + "," + value + "," + year + "," + month + ");";
		DBManager.executeStatement(sql);
	}
}
