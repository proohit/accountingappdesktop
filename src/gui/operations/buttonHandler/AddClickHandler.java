package gui.operations.buttonHandler;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.operations.RecordOperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class AddClickHandler implements EventHandler<ActionEvent> {
    Stage addWindow;

    public AddClickHandler(Stage stage) {
        addWindow = stage;
    }

    @Override
    public void handle(ActionEvent arg0) {
        LocalDate date = RecordOperationHandler.getSelectedDate().getValue();
        Record rec;
        if (date == null) {
            rec = new Record(RecordOperationHandler.getDescriptionField(), RecordOperationHandler.getValueField(),
                    RecordOperationHandler.getWalletField());

        } else {
            rec = new Record(date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + String.format("%02d", date.getDayOfMonth()) + " 00:00:00", RecordOperationHandler.getDescriptionField(), RecordOperationHandler.getValueField(),
                    RecordOperationHandler.getWalletField());
        }

        try {
            RecordTable.insertValues(rec);
            addWindow.hide();
            rec = RecordTable.getById(rec.getId());
            Ui.records.refreshForMonth(rec.getYear() + "-" + rec.getMonth());
            Ui.wallets.refreshAll();
            Ui.months.refreshAll();
            RecordOperationHandler.getWalletList().getItems().clear();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

}
