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
		DBManager.printTables();
		DBManager.printTable("Test");
	}

}
