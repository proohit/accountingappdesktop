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
	static HBox wallets;
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
		operations = new OperationsBox();
		
		months.refreshAll();
		
		
		layout.setPrefSize(600, 400);
		
		layout.setLeft(months);
		layout.setBottom(walletBox());
		layout.setCenter(records);
		layout.setRight(operations);
		stage.setScene(new Scene(layout));
		stage.setTitle("Accounting App");
		GridPane.setHalignment(operations, HPos.CENTER);
	
		stage.show();
	}

	private HBox walletBox() {
		wallets = new HBox();
		wallets.setPrefWidth(100);
		
		return wallets;
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
