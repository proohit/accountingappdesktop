package Application;

import DBConnection.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class DBTest {

	public static void main(String[] args) {
		DBManager db = new DBManager();
		db.createDB("Accounting");
		String[] cols = { "id","name"};
		String[] colType = { "int","varchar"};
		db.executeStatement("DROP TABLE IF EXISTS Test;");
		db.createTable("Test", cols, colType, "id");
		db.executeStatement("INSERT INTO Test(name) VALUES('test');");
		//db.createTable("test", cols, colType, "id");
		try {
		ArrayList<String> tables = db.tables();
		for (String name:tables) {
			System.out.print(name + " ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
