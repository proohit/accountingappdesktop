package gui.operations;

import gui.OperationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OperationEventHandler implements EventHandler<ActionEvent> {

	OperationHyperlink operation;

	public OperationEventHandler(OperationHyperlink op) {
		this.operation = op;
	}

	@Override
	public void handle(ActionEvent arg0) {
		switch (operation.type) {
		case "addRecord": {
			OperationHandler.showAddWindow().show();
			break;
		}
		case "deleteRecord": {
			OperationHandler.deleteRecordWindow().show();
			break;
		}
		case "editRecord": {
			OperationHandler.showEditWindow().show();
			break;
		}
		}
	}

}
