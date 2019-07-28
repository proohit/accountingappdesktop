package gui;

import java.sql.SQLException;

import DBTables.RecordTable;
import data.Record;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddButton extends Button {

	AddButton() {
		super("add");
		this.setOnAction(new EventHandler<ActionEvent>() {

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
		});
	}
}
