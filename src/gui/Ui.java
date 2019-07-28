package gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.util.ArrayList;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;
import gui.months.MonthsBox;
import gui.operations.OperationsBox;
import gui.records.RecordsTableView;
import gui.wallets.WalletBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application {
	BorderPane layout;
	static MonthsBox months;
	static ScrollPane monthsScrollPane;
	
	static ScrollPane walletsScrollPane;
	static WalletBox wallets;
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
		months = new MonthsBox();
		months.refreshAll();
		wallets= new WalletBox();
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
		layout.setCenter(records);
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
	
	private Stage addScreen() {
		AddButton bAddButton = new AddButton("add");
		return bAddButton.showAddWindow();
	}
}
