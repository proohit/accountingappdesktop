package gui.operations;

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
			RecordOperationHandler.showAddWindow().show();
			break;
		}
		case "deleteRecord": {
			RecordOperationHandler.deleteRecordWindow().show();
			break;
		}
		case "editRecord": {
			RecordOperationHandler.showEditWindow().show();
			break;
		}
		case "addWallet": {
			WalletOperationHandler.addWindow().show();
		}
		}
	}

}
