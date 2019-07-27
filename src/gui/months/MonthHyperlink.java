package gui.months;

import javafx.scene.control.Hyperlink;

public class MonthHyperlink extends Hyperlink {

	MonthHyperlink(String text) {
		super(text);
		this.setOnAction(new HyperlinkEventHandler(this));
		this.setStyle("-fx-border: 10px;");
	}
}
