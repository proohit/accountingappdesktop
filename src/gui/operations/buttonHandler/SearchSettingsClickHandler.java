package gui.operations.buttonHandler;

import gui.settings.SearchSettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchSettingsClickHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent arg0) {
        Stage settingsWindow = new Stage();
        GridPane layout = new GridPane();

        layout.setPrefSize(300, 150);
        layout.setPadding(new Insets(0, 10, 0, 10));
        layout.setHgap(15);
        layout.setVgap(15);

        CheckBox globalSearchCheckBox = new CheckBox("global search");
        globalSearchCheckBox.setSelected(SearchSettings.isGlobalSearch());
        globalSearchCheckBox.setTooltip(new Tooltip("when this is enabled, the given String will be searched across all records. \n"
                + "when this is disabled, the given String will be searched across the last selected records (e.g. a month)"));
        Button okButton = new Button("ok");
        Button cancelButton = new Button("cancel");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                SearchSettings.setGlobalSearch(globalSearchCheckBox.isSelected());
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
        layout.add(okButton, 0, 1);
        layout.add(cancelButton, 1, 1);
        Scene settingScene = new Scene(layout);
        settingsWindow.setScene(settingScene);
        settingsWindow.show();
    }
}
