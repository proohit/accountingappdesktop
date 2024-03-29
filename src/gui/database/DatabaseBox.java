package gui.database;

import java.io.File;
import java.sql.SQLException;

import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import gui.Ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatabaseBox extends MenuBar {

    public DatabaseBox() {
        super();

    }

    public void initialize() {
        Stage stage = (Stage) this.getScene().getWindow();
        this.setStyle("-fx-padding: 10,0,0,10;");

        Menu settingsMenu = new Menu("Settings");
        CheckMenuItem globalSearch = new CheckMenuItem("globalSearch");
        globalSearch.setSelected(Ui.configurator.isGlobalSearch());
        MenuItem searchHeader = new MenuItem("Search settings");
        searchHeader.setStyle("-fx-text-fill: grey;");
        searchHeader.setDisable(true);
        globalSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Ui.configurator.writeEntry("globalSearch=" + globalSearch.isSelected());
            }
        });
        settingsMenu.getItems().add(globalSearch);
        settingsMenu.getItems().add(searchHeader);
        Menu fileMenu = new Menu("File");
        MenuItem newDatabase = new MenuItem("new database...");
        newDatabase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("create new Database");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database File", "*.db"));
                File newFile = fileChooser.showSaveDialog(stage);
                if (newFile != null) {
                    DBManager.createDB(newFile.getAbsolutePath());
                    try {
                        RecordTable.createTable();
                        WalletTable.createTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Ui.records.clear();
                    Ui.months.refreshAll();
                    Ui.wallets.refreshAll();
                    stage.setTitle("Accounting App - " + newFile.getName());
                    Alert createdDb = new Alert(AlertType.INFORMATION);
                    createdDb.setContentText("the database has been created at " + newFile.getAbsolutePath());
                    createdDb.show();

                    createdDb.setOnHidden(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            if (Ui.configurator.isDefault(newFile.getAbsolutePath())) {
                                ButtonType yes = new ButtonType("yes");
                                ButtonType no = new ButtonType("no");
                                Alert confirmation = new Alert(AlertType.NONE, "would you like to save this database as default? \n" +
                                        "everytime you open the app, this database will be opened", yes, no);
                                confirmation.showAndWait().ifPresent(result -> {
                                    if (result == yes) {
                                        Ui.configurator.writeEntry("defaultDb=" + newFile.getAbsolutePath());
                                    } else if (result == no) {
                                        confirmation.close();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        MenuItem openDatabase = new MenuItem("open database...");
        openDatabase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("open existing database");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database File", "*.db"));
                File existingFile = fileChooser.showOpenDialog(stage);
                if (existingFile != null) {
                    DBManager.createDB(existingFile.getAbsolutePath());
                    Ui.months.refreshAll();
                    Ui.wallets.refreshAll();
                    Ui.records.clear();
                    stage.setTitle("Accounting App - " + existingFile.getName());
                    Alert confirmation = new Alert(AlertType.INFORMATION);
                    confirmation.setContentText("the database has been loaded from " + existingFile.getAbsolutePath());
                    confirmation.show();

                    confirmation.setOnHidden(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            if (Ui.configurator.isDefault(existingFile.getAbsolutePath())) {
                                ButtonType yes = new ButtonType("yes");
                                ButtonType no = new ButtonType("no");
                                Alert confirmation = new Alert(AlertType.NONE, "would you like to save this database as default? \n" +
                                        "everytime you open the app, this database will be opened", yes, no);
                                confirmation.showAndWait().ifPresent(result -> {
                                    if (result == yes) {
                                        Ui.configurator.writeEntry("defaultDb=" + existingFile.getAbsolutePath());
                                    } else if (result == no) {
                                        confirmation.close();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        fileMenu.getItems().addAll(newDatabase, openDatabase);
        this.getMenus().add(fileMenu);
        this.getMenus().add(settingsMenu);
    }
}
