package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class DBConnection {
	static Connection conn = null;
	public static void connect(String Path) {
		try {
			String url = "jdbc:sqlite:"+Path+".db";
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to DB established.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to DB.");
		}
	}
	public static boolean connected() {
		return conn != null? true: false;
	}
	public static Statement newStatement() {
			try {
			return conn.createStatement();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't create Statement");
				return null;
			}
	
	}
}
