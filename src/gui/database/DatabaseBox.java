package gui.database;

import java.io.File;
import java.sql.SQLException;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import gui.Ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DatabaseBox extends HBox {

	public DatabaseBox() {
		super();

	}

	public void initialize() {
		Window stage = this.getScene().getWindow();
		this.setStyle("-fx-padding: 10,0,0,10;");
		Hyperlink newDatabase = new Hyperlink("new database...");
		newDatabase.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database File", "*.db"));
				File newFile = fileChooser.showSaveDialog(stage);
				DBManager.createDB(newFile.getAbsolutePath());
				try {
					RecordTable.createTable();
					WalletTable.createTable();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		Hyperlink openDatabase = new Hyperlink("open database...");
		openDatabase.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				File existingFile = fileChooser.showOpenDialog(stage);
				DBManager.createDB(existingFile.getAbsolutePath());
				Ui.months.refreshAll();
				Ui.wallets.refreshAll();
			}
		});

		this.getChildren().add(newDatabase);
		this.getChildren().add(openDatabase);
	}
}
