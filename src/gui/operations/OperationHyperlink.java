package gui.operations;

import javafx.scene.control.Hyperlink;

public class OperationHyperlink extends Hyperlink {
	String type;
	OperationHyperlink(String text, String type) {
		super(text);
		this.type = type;
		this.setOnAction(new OperationEventHandler(this));
	}
}
