package gui;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import gui.config.Configurator;
import gui.database.DatabaseBox;
import gui.months.MonthsBox;
import gui.operations.OperationsBox;
import gui.records.RecordsTableView;
import gui.search.CenterTopAnchor;
import gui.wallets.WalletBox;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

public class Ui extends Application {
	BorderPane layout;
	public static MonthsBox months;
	static ScrollPane monthsScrollPane;
	static CenterTopAnchor middleAnchor;
	static ScrollPane walletsScrollPane;
	public static WalletBox wallets;
	public static RecordsTableView records;
	static OperationsBox operations;
	static DatabaseBox topOperations;
	public static Configurator configurator = new Configurator();
	RecordTable recordTable;
	WalletTable walletTable;

	@Override
	public void start(Stage stage) throws Exception {
		//setDb("Accounting");
		recordTable = new RecordTable();
		walletTable = new WalletTable();

		layout = new BorderPane();
		topOperations = new DatabaseBox();
		records = new RecordsTableView();
		middleAnchor = new CenterTopAnchor();
		months = new MonthsBox();
		months.refreshAll();
		wallets = new WalletBox();
		wallets.refreshAll();
		operations = new OperationsBox();
		monthsScrollPane = new ScrollPane();
		monthsScrollPane.setContent(months);
		monthsScrollPane.setPrefViewportWidth(75);
		walletsScrollPane = new ScrollPane();
		walletsScrollPane.setContent(wallets);

		layout.setPrefSize(800, 500);

		layout.setLeft(monthsScrollPane);
		layout.setBottom(walletsScrollPane);
		layout.setCenter(middleAnchor);
		layout.setRight(operations);
		layout.setTop(topOperations);
		stage.setScene(new Scene(layout));
		stage.setTitle("Accounting App");
		GridPane.setHalignment(operations, HPos.CENTER);
		topOperations.initialize();

        if(configurator.getDefaultDb()!=null) {
            String defaultDb = configurator.getDefaultDb();
            File dbFile = new File(defaultDb);
            setDb(defaultDb);
            stage.setTitle("AccountingApp - " + dbFile.getName());
            months.refreshAll();
            wallets.refreshAll();
        }

        stage.show();
	}

	private void setDb(String dbName) {
		DBManager.createDB(dbName);
	}

	public static void main(String[] args) {
		launch();
	}
}
