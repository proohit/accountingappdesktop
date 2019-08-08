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

public class DeleteClickHandler implements EventHandler<ActionEvent> {
	Stage deleteWindow;
	
	public DeleteClickHandler(Stage deleteWindow) {
		this.deleteWindow=deleteWindow;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent arg0) {
		try {
			Record rec = RecordTable.getById(RecordOperationHandler.getDelId());
			deleteWindow.hide();
			RecordTable.deleteById(rec.getId());
			Ui.wallets.refreshAll();
			Ui.records.refreshForMonth(rec.getYear() + "-" + rec.getMonth());
		} catch (Exception e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(e.getMessage());
			error.show();
		}
	}

}
