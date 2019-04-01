package Application;

import DBConnection.*;
import DBTables.Table;

import java.util.Hashtable;
public class DBTest {

	public static void main(String[] args) {
		DBManager.createDB("Accounting");
		DBManager.executeStatement("DROP TABLE IF EXISTS Test12345;");
		Hashtable<String, String> testHashtable = new Hashtable<String, String>();
		testHashtable.put("id", "int");
		testHashtable.put("name", "varchar");
		String[] primKeyStrings = {"id"};
		Table testTable = new Table("Test12345",testHashtable, primKeyStrings);
		//DBManager.createTable("Test", cols, colType, "id");
		DBManager.executeStatement("INSERT INTO Test12345(name, id) VALUES('test',1);");
		DBManager.executeStatement("INSERT INTO Test12345(name, id) VALUES('test2',2);");
		DBManager.executeStatement("INSERT INTO Test12345(name, id) VALUES('test3',3);");
		DBManager.printTables();
		testTable.printRows();
	}

}
