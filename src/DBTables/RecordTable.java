package DBTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;
import data.Record;

public class RecordTable extends Table {
	public RecordTable() {
		super();
		tableName = "Record";
		columns.put("recordId", "int");
		columns.put("description", "varchar");
		columns.put("value", "double");
		columns.put("walletName", "varchar");
		columns.put("timestamp", "datetime");
	}

	public void insertValues(Record record) throws SQLException {
		String sql = "INSERT INTO " + tableName + "(" + "description," + "value," + "timestamp," + "walletName)"
				+ "VALUES('" + record.getDescription() + "'," + record.getValue() + "," + "DATETIME('now')" + ", '"
				+ record.getWallet() + "');";
		DBManager.executeStatement(sql);
		DBManager.executeStatement("UPDATE Wallet SET balance=balance+" + record.getValue() + " WHERE name=" + "'"
				+ record.getWallet() + "';");
	}

	public void insertValues(int id, String description, double value, String walletName) throws SQLException {
		String sql = "INSERT INTO " + tableName + "(recordId, description, value, timestamp, walletName) VALUES(" + id
				+ "," + "'" + description + "'" + "," + value + "," + "DATETIME('now'), '" + walletName + "');";
		DBManager.executeStatement(sql);
		DBManager.executeStatement(
				"UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';");
	}
	public void updateRecord(Record record, double value, String description, String walletName) throws SQLException {
		String sql="UPDATE Record SET value="+value+", description='"+description+"', walletName='"+walletName+"' WHERE recordId="+record.getId()+";";
		double correctValue = record.getValue()*-1;
		DBManager.executeStatement(sql);
		String sql2 = "UPDATE Wallet SET balance=balance+" + correctValue + " WHERE name=" + "'" + record.getWallet() + "';";
		String sql3 = "UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';";
		DBManager.executeStatement(sql2);
		DBManager.executeStatement(sql3);
	}
	public void createTable() throws SQLException {
		DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Record (" + "recordId INTEGER PRIMARY KEY,"
				+ "description varchar," + "value double," + "walletName varchar," + "timestamp smalldatetime,"
				+ "FOREIGN KEY(walletName) REFERENCES Wallet(name));");
	}
	public Record getById(int id) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE recordId="+id+";";
		return getResult(sql).get(0);
	}
	public ArrayList<Record> getByYear(int year) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE strftime('%Y',timestamp)=='" + year + "';";
		return getResult(sql);
	}
	public ArrayList<Record> getByMonth(int year, String month) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE strftime('%Y-%m',timestamp)=='" + year + "-"+month+"';";
		return getResult(sql);
	}
	public ArrayList<Record> getByMonth(String yearMonth) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE strftime('%Y-%m',timestamp)=='" + yearMonth+"';";
		return getResult(sql);
	}
	public ArrayList<Record> getByDescription(String description) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE description=='"+description+"';";
		return getResult(sql);
	}
	public ArrayList<String> getMonths() throws SQLException {
		String sql = "select DISTINCT strftime('%Y-%m',timestamp) as month from Record ORDER BY month desc";
		ResultSet rs = DBManager.selectStmt(sql);
		ArrayList<String> result = new ArrayList<String>();
		while (rs.next()) {
			result.add(rs.getString("month"));
		}
		return result;
	}
	private ArrayList<Record> getResult(String sql) throws SQLException {
		ResultSet rs = DBManager.selectStmt(sql);
		ArrayList<Record> result = new ArrayList<Record>();
		while (rs.next()) {
			result.add(new Record(rs.getString("timestamp"), rs.getInt("recordId"), rs.getString("description"),
					rs.getDouble("value"), rs.getString("walletName")));
		}
		return result;
	}

	public ArrayList<Record> selectAll() throws SQLException {
		String sql = "SELECT * FROM " + tableName + ";";
		return getResult(sql);
	}
}