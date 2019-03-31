package DBConnection;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class DBManager {
	String name;
	DBConnection connecter;

	public void createDB(String dbName) {
		connecter = new DBConnection();
		if (!connecter.connected()) {
			try {
				connecter.connect(dbName);
				DatabaseMetaData meta = connecter.conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't create DB.");
			}
		}
	}

	public void createTable(String tableName, String cols[], String colType[], String primKey) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n";
		if (cols.length != colType.length) {
			System.out.println("ERROR: column array doesnt match column type array");
			return;
		} else {
			for (int i = 0; i < cols.length; i++) {
				sql += cols[i] + " " + colType[i] + ",\n";
			}
			sql += "PRIMARY KEY(" + primKey + "));";
		}
		executeStatement(sql);
	}
	public void executeStatement(String sql) {
		try {
			System.out.println(connecter.newStatement().executeUpdate(sql)+ " "
					+ "rows affected.");
			System.out.println("executed query:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't execute Query");
		}
	}
	public ResultSet selectStmt(String sql) throws SQLException{
		try {
			return connecter.newStatement().executeQuery(sql);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	public ArrayList<String> tables() throws SQLException{
		try {
			ResultSet tables = connecter.conn.getMetaData().getTables(null, null, "%", null);
			ArrayList<String> tableNames = new ArrayList<String>();
			for(int i = 0; tables.next();i++) {
				tableNames.add(tables.getString(3));
			}
			return tableNames;
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
}
