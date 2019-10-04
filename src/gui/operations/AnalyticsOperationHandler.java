package gui.operations;

import DBTables.RecordTable;
import data.Wallet;
import gui.operations.buttonHandler.AddClickHandler;
import gui.operations.buttonHandler.EvaluateMonthClickHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AnalyticsOperationHandler {

    static GridPane operationPane = new GridPane();
    static Label monthLabel = new Label("month");
    static ComboBox<String> monthList = new ComboBox<>();

    private static void initializeGrid() {
        operationPane = new GridPane();
        operationPane.setPrefSize(300, 150);
        operationPane.setPadding(new Insets(0, 10, 0, 10));
        operationPane.setHgap(15);
    }

    public static Stage showMonthEvaluation() {
        initializeGrid();

        final Stage monthSelectionWindow = new Stage();

        Button cancelButton = new Button("cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                monthSelectionWindow.hide();
                monthList.getItems().clear();
            }
        });

        Button addButton = new Button("evaluate");

        addButton.setOnAction(new EvaluateMonthClickHandler());

        fillMonthList();

        operationPane.add(monthLabel, 0, 0);
        operationPane.add(monthList, 1, 0);
        operationPane.add(addButton, 0, 1);
        operationPane.add(cancelButton, 1, 1);

        Scene selectScene = new Scene(operationPane);
        monthSelectionWindow.setScene(selectScene);
        monthSelectionWindow.setTitle("select a month");
        return monthSelectionWindow;
    }

    private static void fillMonthList() {
        try {
            monthList.getItems().clear();
            RecordTable.getMonths().forEach(month -> {
                monthList.getItems().add(month);
            });
        } catch (SQLException e) {

        }
    }

    public static String getMonthSelection() {
        return monthList.getValue();
    }
}
