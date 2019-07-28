package gui.operations.buttonHandler;

import DBTables.RecordTable;
import data.Record;
import gui.OperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddClickHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Record rec = new Record(OperationHandler.getDescriptionField(), OperationHandler.getValueField(),
				OperationHandler.getWalletField());

		try {
			RecordTable.insertValues(rec);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.show();
		}

	}

}
