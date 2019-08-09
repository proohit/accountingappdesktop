package gui;

import java.util.ArrayList;
import java.util.Iterator;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;
import gui.months.MonthsBox;
import gui.operations.OperationsBox;
import gui.records.RecordsTableView;
import gui.wallets.WalletBox;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ui extends Application {
	BorderPane layout;
	static MonthsBox months;
	static ScrollPane monthsScrollPane;
	static AnchorPane middleAnchor;
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
		initializeSearchBar();
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

	private void initializeSearchBar() {
		//Setting Button, um Suchoptionen festzulegen
		middleAnchor = new AnchorPane();
		HBox searchBar = new HBox(4);
		TextField searchField = new TextField();
		Button gearButton = new Button();
		Image gearIcon = new Image("file:res/gear_icon.png",20, 20, false, false);
		gearButton.setGraphic(new ImageView(gearIcon));
		searchBar.getChildren().addAll(gearButton, searchField);
		searchBar.setAlignment(Pos.CENTER_RIGHT);
		searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					
					ArrayList<Record> searchList = new ArrayList<Record>();
					ArrayList<Record> currentItems = records.getCurrentItems();
					currentItems.stream().forEach(item -> {
						if (item.getDescription().contains(searchField.getText())
								|| Integer.toString(item.getId()).contains(searchField.getText())
								|| item.getTimestamp().contains(searchField.getText())) {
							searchList.add(item);
						}
					});
					records.clear();
					searchList.stream().forEach(item -> {
						records.add(item);
					});
				}
			}
		});
		searchField.setPromptText("search records..");
		AnchorPane.setRightAnchor(searchBar, 0.0);
		AnchorPane.setTopAnchor(searchBar, 10.0);

		AnchorPane.setTopAnchor(records, 40.0);
		AnchorPane.setBottomAnchor(records, 0.0);
		AnchorPane.setRightAnchor(records, 0.0);
		AnchorPane.setLeftAnchor(records, 0.0);

		middleAnchor.getChildren().add(searchBar);
		middleAnchor.getChildren().add(records);

	}

	private void setDb(String dbName) {
		DBManager.createDB(dbName);
	}

	public static void main(String[] args) {
		launch();
	}
}
