package gui.operations.buttonHandler;

import DBTables.RecordTable;
import gui.operations.OperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteClickHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		try {
			RecordTable.deleteById(OperationHandler.getDelId());
		} catch (Exception e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(e.getMessage());
			error.show();
		}
	}

}
