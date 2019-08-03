package gui.operations.buttonHandler;

import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.operations.OperationHandler;
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
		Record rec = new Record(OperationHandler.getDescriptionField(), OperationHandler.getValueField(),
				OperationHandler.getWalletField());

		try {
			RecordTable.insertValues(rec);
			addWindow.hide();
			Ui.records.clear();
			ArrayList<Record> records = RecordTable.getByMonth(rec.getYear() + "-"+ rec.getMonth());
			records.stream().forEach(month -> {
				Ui.records.add(month);
			});
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.show();
		}

	}

}
