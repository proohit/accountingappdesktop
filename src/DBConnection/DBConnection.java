package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static Connection conn = null;

	public static void connect(String Path) {
		try {
			if (conn == null) {
				String url = "jdbc:sqlite:" + Path + ".db";
				conn = DriverManager.getConnection(url);
				System.out.println("Connection to DB established.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to DB.");
		}
	}

	public static boolean connected() {
		return conn != null ? true : false;
	}
	public static Connection getConnection() {
		if(connected()) return conn;
		else return null;
	}
	public static Statement newStatement() throws SQLException {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't create Statement");
			throw new SQLException();
		}

	}
}
