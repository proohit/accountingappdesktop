package gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;
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
	VBox months;
	HBox wallets;
	RecordsTableView records;
	VBox operations;
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
		records.add(new Record("test123", 123, "Konto"));
		records.add(new Record("test12123", 12123, "Konto"));
		records.add(new Record("test121233", 12123, "Konto"));
		
		layout.setPrefSize(600, 400);
		
		layout.setLeft(monthsBox());
		layout.setBottom(walletBox());
		layout.setCenter(records);
		layout.setRight(operationsBox());
		stage.setScene(new Scene(layout));
		stage.setTitle("Accounting App");
		GridPane.setHalignment(operations, HPos.CENTER);
	
		stage.show();
	}
	private VBox monthsBox() {
		months = new VBox();
		months.getChildren().add(new Label("Months"));
		months.setStyle("-fx-padding: 10,0,0,0;");
		try {
			ArrayList<String> months = recordTable.getMonths();
			months.stream().forEach(month -> {
				Label monthLabel = new Label(month);
				GridPane.setHalignment(monthLabel, HPos.CENTER);
				this.months.getChildren().add(monthLabel);
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return months;
	}
	private HBox walletBox() {
		wallets = new HBox();
		wallets.setPrefWidth(100);
		
		return wallets;
	}
	private TableView<Record> recordGrid() {
		records = new TableView<Record>();
		
		Label id = new Label("recordId");
		Label description = new Label("description");
		Label value = new Label("value");
		
		TableColumn nameColumn = new TableColumn("Id");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

		TableColumn surnameColumn = new TableColumn("Description");
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

		TableColumn valueColumn = new TableColumn("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("Value"));
		
		TableColumn walletColumn = new TableColumn("Wallet");
		walletColumn.setCellValueFactory(new PropertyValueFactory<>("Wallet"));
		
		
		records.getColumns().addAll(nameColumn, surnameColumn, valueColumn, walletColumn);

		return records;
	}
	private VBox operationsBox() {
		operations = new VBox();
		Hyperlink operationLabel = new Hyperlink("add record...");
		operationLabel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage addWindow = addScreen();
				addWindow.show();
			}
		});
		BorderPane.setAlignment(operationLabel, Pos.CENTER);
		operations.getChildren().add(operationLabel);
		
		operations.setPrefWidth(100);
		return operations;
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
