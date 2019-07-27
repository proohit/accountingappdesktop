package gui.operations;

import gui.ButtonHandler;
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
		case "add": {
			ButtonHandler.showAddWindow().show();
			break;
		}
		}
	}

}
