package gui.operations.buttonHandler;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.operations.RecordOperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EditClickHandler implements EventHandler<ActionEvent> {
	Stage editRecordWindow;
	Record rec;
	public EditClickHandler(Stage window, Record rec) {
		editRecordWindow=window;
		this.rec = rec;
	}
	@Override
	public void handle(ActionEvent arg0) {
		try {
			editRecordWindow.hide();
			RecordTable.updateRecord(rec, RecordOperationHandler.getValueField(), RecordOperationHandler.getDescriptionField(), RecordOperationHandler.getWalletField(),
					RecordOperationHandler.getSelectedDate().getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			Ui.records.refreshForMonth(rec.getYear() + "-" + rec.getMonth());
			Ui.wallets.refreshAll();
			Ui.records.getSelectionModel().clearSelection();
			Ui.months.refreshAll();
			RecordOperationHandler.getWalletList().getItems().clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
