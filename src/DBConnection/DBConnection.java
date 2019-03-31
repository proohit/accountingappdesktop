package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class DBConnection {
	Connection conn = null;
	public void connect() {
		try {
			String url = "jdbc:sqlite:test.db";
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to DB established.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to DB.");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void connect(String Path) {
		try {
			String url = "jdbc:sqlite:"+Path+".db";
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to DB established.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to DB.");
		}
	}
	public boolean connected() {
		return conn != null? true: false;
	}
	public Statement newStatement() {
			try {
			return conn.createStatement();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't create Statement");
				return null;
			}
	
	}
}
