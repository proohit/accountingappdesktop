package gui;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ui extends Application {
	BorderPane layout;
	static MonthsBox months;
	static ScrollPane monthsScrollPane;
	static CenterTopAnchor middleAnchor;
	static ScrollPane walletsScrollPane;
	public static WalletBox wallets;
	public static RecordsTableView records;
	static OperationsBox operations;
	HBox topOperations;

	RecordTable recordTable;
	WalletTable walletTable;

	@Override
	public void start(Stage stage) throws Exception {
		setDb("Accounting");
		recordTable = new RecordTable();
		walletTable = new WalletTable();

		layout = new BorderPane();

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
		stage.setScene(new Scene(layout));
		stage.setTitle("Accounting App");
		GridPane.setHalignment(operations, HPos.CENTER);

		stage.show();
	}
	
	private void setDb(String dbName) {
		DBManager.createDB(dbName);
	}

	public static void main(String[] args) {
		launch();
	}
}
