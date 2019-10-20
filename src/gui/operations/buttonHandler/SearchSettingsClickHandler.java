package gui.operations.buttonHandler;

import gui.Ui;
import gui.settings.SearchSettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SearchSettingsClickHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent arg0) {
        Stage settingsWindow = new Stage();
        GridPane layout = new GridPane();

        layout.setPrefSize(500, 150);
        layout.setPadding(new Insets(0, 10, 0, 10));
        layout.setHgap(15);
        layout.setVgap(15);

        CheckBox globalSearchCheckBox = new CheckBox("global search");
        globalSearchCheckBox.setSelected(SearchSettings.isGlobalSearch());
        globalSearchCheckBox.setTooltip(new Tooltip("when this is enabled, the given String will be searched across all records. \n"
                + "when this is disabled, the given String will be searched across the last selected records (e.g. a month)"));
        Label fileLabel = new Label("default database");
        TextArea filePath = new TextArea();
        filePath.setTooltip(new Tooltip("in this field, you can specify a default database which will automatically load upon starting the app."));
        filePath.setPrefHeight(40);
        filePath.setPrefWidth(300);
        if(Ui.configurator.getDefaultDb() != null) filePath.setText(Ui.configurator.getDefaultDb());
        Button pathButton = new Button("...");
        pathButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fc = new FileChooser();
                fc.setTitle("set default database");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Database Files", "*.db"));
                File file = fc.showOpenDialog(settingsWindow);
                if(file != null) filePath.setText(file.getAbsolutePath());
            }
        });
        Button okButton = new Button("ok");
        Button cancelButton = new Button("cancel");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Ui.configurator.writeEntry("defaultDb=" + filePath.getText());
                settingsWindow.hide();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                settingsWindow.hide();
            }
        });
        layout.add(globalSearchCheckBox, 0, 0);
        layout.add(fileLabel, 0, 1);
        layout.add(filePath,1,1);
        layout.add(pathButton, 2, 1);
        layout.add(okButton, 0, 2);
        layout.add(cancelButton, 1, 2);
        Scene settingScene = new Scene(layout);
        settingsWindow.setScene(settingScene);
        settingsWindow.show();
    }
}
