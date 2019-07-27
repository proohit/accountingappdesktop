package gui.operations;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OperationsBox extends VBox {
	public OperationsBox() {
		super();
		Label operationsLabel = new Label("Operations");
		operationsLabel.setStyle("-fx-font-size: 14px;");
		this.add(operationsLabel);
		this.setStyle("-fx-padding: 10,0,0,0;");
		
		this.add(new OperationHyperlink("add record..", "add"));
	}

	public void add(Label label) {
		this.getChildren().add(label);
	}

	public void add(OperationHyperlink link) {
		this.getChildren().add(link);
	}
}
