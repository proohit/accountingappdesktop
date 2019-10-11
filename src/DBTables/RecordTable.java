package DBTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DBConnection.DBConnection;
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

    public static double getSumOfMonth(String month) throws SQLException {
        ResultSet rs = DBManager.selectStmt("select sum(value) from Record where strftime('%Y-%m', timestamp) == '" + month + "';");
        return rs.getDouble("sum(value)");
    }

    public static List<Record> getEvaluationOfMonth(String month) throws SQLException {
        List<Record> evaluationList = new ArrayList<>();
        ResultSet rs = DBManager.selectStmt("select sum(value), description from Record where strftime('%Y-%m', timestamp) =='" + month + "' GROUP by description order by sum(value) desc");
        while (rs.next()) {
            evaluationList.add(new Record(rs.getString("description"), rs.getDouble("sum(value)"), null));
        }
        return evaluationList;
    }

    public static void insertValues(Record record) throws Exception {
        WalletTable.contains(record.getWallet());
        if (record.getDescription().contains("'")) {
            record.setDescription(record.getDescription().replaceAll("'", "''"));
        }
        String sql;
        if (record.getTimestamp2() == null) {
            sql = "INSERT INTO " + "Record" + "(" + "description," + "value," + "timestamp," + "walletName)"
                    + "VALUES('" + record.getDescription() + "'," + record.getValue() + "," + "DATETIME('now')" + ", '"
                    + record.getWallet() + "');";
        } else {
            sql = "INSERT INTO " + "Record" + "(" + "description," + "value," + "timestamp," + "walletName)"
                    + "VALUES('" + record.getDescription() + "'," + record.getValue() + "," + "strftime('%Y-%m-%d %H:%M:%S','" + record.getTimestamp() + "')" + ", '"
                    + record.getWallet() + "');";
        }

        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
        if (generatedKeys.next()) {
            record.setId(generatedKeys.getInt("last_insert_rowid()"));
        }
        DBManager.executeStatement("UPDATE Wallet SET balance=balance+" + record.getValue() + " WHERE name=" + "'"
                + record.getWallet() + "';");
    }

    public static void insertValues(int id, String description, double value, String walletName) throws Exception {
        WalletTable.contains(walletName);
        if (description.contains("'")) {
            description = description.replaceAll("'", "''");
        }
        String sql = "INSERT INTO " + "Record" + "(recordId, description, value, timestamp, walletName) VALUES(" + id
                + "," + "'" + description + "'" + "," + value + "," + "DATETIME('now'), '" + walletName + "');";
        DBManager.executeStatement(sql);
        DBManager.executeStatement(
                "UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';");
    }

    public static void updateRecord(Record record, double value, String description, String walletName)
            throws SQLException {
        String sql = "UPDATE Record SET value=" + value + ", description='" + description + "', walletName='"
                + walletName + "' WHERE recordId=" + record.getId() + ";";
        double correctValue = record.getValue() * -1;
        DBManager.executeStatement(sql);
        String sql2 = "UPDATE Wallet SET balance=balance+" + correctValue + " WHERE name=" + "'" + record.getWallet()
                + "';";
        String sql3 = "UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';";
        DBManager.executeStatement(sql2);
        DBManager.executeStatement(sql3);
    }
    public static void updateRecord(Record record, double value, String description, String walletName, String timestamp)
            throws SQLException {
        String sql = "UPDATE Record SET value=" + value + ", description='" + description + "', walletName='"
                + walletName + "', timestamp=strftime('%Y-%m-%d %H:%M:%S','" + timestamp + "') WHERE recordId=" + record.getId() + ";";
        double correctValue = record.getValue() * -1;
        DBManager.executeStatement(sql);
        String sql2 = "UPDATE Wallet SET balance=balance+" + correctValue + " WHERE name=" + "'" + record.getWallet()
                + "';";
        String sql3 = "UPDATE Wallet SET balance=balance+" + value + " WHERE name=" + "'" + walletName + "';";
        DBManager.executeStatement(sql2);
        DBManager.executeStatement(sql3);
    }
    public static void createTable() throws SQLException {
        DBManager.executeStatement("CREATE TABLE IF NOT EXISTS Record (" + "recordId INTEGER PRIMARY KEY,"
                + "description varchar," + "value double," + "walletName varchar," + "timestamp smalldatetime,"
                + "FOREIGN KEY(walletName) REFERENCES Wallet(name));");
    }

    public static ArrayList<Record> getAll() {
        String sql = "SELECT * FROM Record;";

        try {
            return getResult(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Record getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + "Record" + " WHERE recordId=" + id + ";";
        return getResult(sql).get(0);
    }

    public static ArrayList<Record> getByYear(int year) throws SQLException {
        String sql = "SELECT * FROM " + "Record" + " WHERE strftime('%Y',timestamp)=='" + year + "';";
        return getResult(sql);
    }

    public static ArrayList<Record> getByMonth(int year, String month) throws SQLException {
        String sql = "SELECT * FROM " + "Record" + " WHERE strftime('%Y-%m',timestamp)=='" + year + "-" + month + "';";
        return getResult(sql);
    }

    public static ArrayList<Record> getByMonth(String yearMonth) throws SQLException {
        String sql = "SELECT * FROM " + "Record" + " WHERE strftime('%Y-%m',timestamp)=='" + yearMonth + "';";
        return getResult(sql);
    }

    public static ArrayList<Record> getByDescription(String description) throws SQLException {
        String sql = "SELECT * FROM " + "Record" + " WHERE description=='" + description + "';";
        return getResult(sql);
    }

    public static ArrayList<String> getMonths() throws SQLException {
        String sql = "select DISTINCT strftime('%Y-%m',timestamp) as month from Record ORDER BY month desc";
        ResultSet rs = DBManager.selectStmt(sql);
        ArrayList<String> result = new ArrayList<String>();
        while (rs.next()) {
            result.add(rs.getString("month"));
        }
        return result;
    }

    private static ArrayList<Record> getResult(String sql) throws SQLException {
        ResultSet rs = DBManager.selectStmt(sql);
        ArrayList<Record> result = new ArrayList<Record>();
        while (rs.next()) {
            result.add(new Record(rs.getString("timestamp"), rs.getInt("recordId"), rs.getString("description"),
                    rs.getDouble("value"), rs.getString("walletName")));
        }
        return result;
    }

    public static ArrayList<Record> selectAll() throws SQLException {
        String sql = "SELECT * FROM " + "Record" + ";";
        return getResult(sql);
    }

    public static ArrayList<Record> getByWallet(String wallet) throws SQLException {
        String sql = "SELECT * FROM Record WHERE walletName = '" + wallet + "'";
        ArrayList<Record> result = new ArrayList<Record>();

        ResultSet rs = DBManager.selectStmt(sql);
        while (rs.next()) {
            result.add(new Record(rs.getString("timestamp"), rs.getInt("recordId"), rs.getString("description"),
                    rs.getDouble("value"), rs.getString("walletName")));
        }
        return result;
    }

    public static void deleteById(int id) throws Exception {
        Record rec = getById(id);
        String sql = "DELETE FROM Record WHERE recordId=" + id;
        String walletString = "UPDATE Wallet SET balance=balance-" + rec.getValue() + " WHERE name='" + rec.getWallet()
                + "';";
        try {
            DBManager.executeStatement(sql);
            DBManager.executeStatement(walletString);
        } catch (SQLException e) {
            throw new Exception("record not available");
        }
    }
}
