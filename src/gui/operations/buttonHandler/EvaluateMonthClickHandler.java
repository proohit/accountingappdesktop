package gui.operations.buttonHandler;

import DBTables.RecordTable;
import data.Record;
import gui.operations.AnalyticsOperationHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EvaluateMonthClickHandler implements EventHandler {


    @Override
    public void handle(Event event) {
        Stage evaluateWindow = new Stage();
        VBox screen = new VBox();

        TableColumn<Record, String> descriptionCol = new TableColumn<>("description");
        TableColumn<Record, Double> valueCol = new TableColumn<>("value");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Record, String>("description"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Record, Double>("Value"));

        TableView<Record> evalTable = new TableView<>();
        Button triggerBut = (Button) event.getSource();
        evalTable.getColumns().addAll(descriptionCol, valueCol);
        try {
            if(AnalyticsOperationHandler.getMonthSelection() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("invalid month input");
                alert.show();
                return;
            }
            List<Record> result = RecordTable.getEvaluationOfMonth(AnalyticsOperationHandler.getMonthSelection());
            result.forEach((record) -> {evalTable.getItems().add(record);});

            screen.getChildren().add(evalTable);
            double monthlySum = RecordTable.getSumOfMonth(AnalyticsOperationHandler.getMonthSelection());
            Label sumLabel = new Label("total difference: " + Double.toString(monthlySum));
            if(monthlySum>0) {
                sumLabel.setStyle("-fx-background-color: greenyellow");
            }else {
                sumLabel.setStyle("-fx-background-color: red");
            }
            screen.getChildren().add(sumLabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }




        Scene evaluateScene = new Scene(screen);
        evaluateWindow.setScene(evaluateScene);
        evaluateWindow.setTitle("Evaluation " + AnalyticsOperationHandler.getMonthSelection());
        evaluateWindow.show();
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}
