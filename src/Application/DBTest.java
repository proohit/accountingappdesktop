package Application;

import DBConnection.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class DBTest {

	public static void main(String[] args) {
		DBManager.createDB("Accounting");
		String[] cols = { "id","name"};
		String[] colType = { "int","varchar"};
		DBManager.executeStatement("DROP TABLE IF EXISTS Test;");
		DBManager.createTable("Test", cols, colType, "id");
		DBManager.executeStatement("INSERT INTO Test(name) VALUES('test');");
		//db.createTable("test", cols, colType, "id");
		try {
		ArrayList<String> tables = DBManager.tables();
		for (String name:tables) {
			System.out.print(name + " ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
