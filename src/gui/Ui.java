package gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
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
import javafx.scene.control.TextField;
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
	ScrollPane recordPane;
	AnchorPane anchorPane;
	GridPane records;
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
		layout.setPrefSize(600, 400);
		recordPane = new ScrollPane();
		recordPane.setFitToWidth(true);
		recordPane.setContent(recordGrid());
		layout.setLeft(monthsBox());
		layout.setBottom(walletBox());
		layout.setCenter(recordPane);
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
	private GridPane recordGrid() {
		records = new GridPane();
		records.setHgap(10);
		records.setVgap(10);
		records.setGridLinesVisible(true);
		Label id = new Label("recordId");
		Label description = new Label("description");
		Label value = new Label("value");
		records.add(id, 0, 0);
		records.add(description, 1, 0);
		records.add(value, 2, 0);
		GridPane.setFillWidth(id, true);
		GridPane.setFillWidth(description, true);
		GridPane.setFillWidth(value, true);
		GridPane.setHalignment(id, HPos.CENTER);
		GridPane.setHalignment(description, HPos.CENTER);
		GridPane.setHalignment(value, HPos.CENTER);
		records.setAlignment(Pos.CENTER);
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
