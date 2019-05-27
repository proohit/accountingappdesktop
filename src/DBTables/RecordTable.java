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
	columns.put("timestamp", "smalldatetime");
    }

    public void insertValues(Record record) {
	String sql = "INSERT INTO " + tableName + "("
		+ "recordId,"
		+ "description,"
		+ "value,"
		+ "timestamp,"
		+ "walletName)"
		+ "VALUES("
		+ record.getId() + ",'"
		+ record.getDescription() + "',"
		+ record.getValue() + ","
		+ record.getTimestamp() + ","
		+ record.getWallet() + "');";
	DBManager.executeStatement(sql);
	DBManager.executeStatement(
		"UPDATE Wallet SET balance=balance+" + record.getValue() + " WHERE name=" + "'" + record.getWallet() + "';");
    }

    public void insertValues(int id, String description, double value, String walletName) {
	String sql = "INSERT INTO " + tableName + "(recordId, description, value, timestamp, walletName) VALUES(" + id
		+ "," + "'" + description + "'" + "," + value + "," + "strftime('%Y.%m.%d-%H:%M:%S'), '" + walletName
		+ "');";
	DBManager.executeStatement(sql);
	DBManager.executeStatement(
		"UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';");
    }

    public void createTable() {
	DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Record (" + "recordId int AUTO INCREMENT,"
		+ "description varchar," + "value double," + "walletName varchar," + "timestamp smalldatetime,"
		+ "PRIMARY KEY(recordId)," + "FOREIGN KEY(walletName) REFERENCES Wallet(name));");
    }

    public ArrayList<Record> selectAll() throws SQLException {
	String sql = "SELECT * FROM " + tableName + ";";
	ResultSet rs = DBManager.selectStmt(sql);
	ArrayList<Record> result = new ArrayList<Record>();
	while (rs.next()) {
	    result.add(new Record(rs.getString("timestamp"), rs.getInt("recordId"), rs.getString("description"),
		    rs.getDouble("value"), rs.getString("walletName")));
	}
	return result;
    }
}
