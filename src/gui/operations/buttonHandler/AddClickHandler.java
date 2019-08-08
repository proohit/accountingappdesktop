package gui.operations.buttonHandler;

import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.operations.RecordOperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddClickHandler implements EventHandler<ActionEvent> {
	Stage addWindow;
	
	public AddClickHandler(Stage stage) {
		addWindow=stage;
	}
	@Override
	public void handle(ActionEvent arg0) {
		Record rec = new Record(RecordOperationHandler.getDescriptionField(), RecordOperationHandler.getValueField(),
				RecordOperationHandler.getWalletField());

		try {
			RecordTable.insertValues(rec);
			addWindow.hide();
			rec = RecordTable.getById(rec.getId());
			Ui.records.refreshForMonth(rec.getYear() + "-" + rec.getMonth());
			Ui.wallets.refreshAll();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.show();
		}

	}

}
