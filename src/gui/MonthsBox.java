package gui;

import java.util.ArrayList;

import DBTables.RecordTable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MonthsBox extends VBox {

	public MonthsBox() {
		super();
		Label monthsLabel = new Label("Months");
		monthsLabel.setStyle("-fx-font-size: 14px;");
		this.add(monthsLabel);
		this.setStyle("-fx-padding: 10,0,0,0;");
	}

	public void refreshAll() {
		try {
			ArrayList<String> months = RecordTable.getMonths();
			months.stream().forEach(month -> {
				MonthHyperlink monthLink = new MonthHyperlink(month);
				this.add(monthLink);
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void add(Label label) {
		this.getChildren().add(label);
	}
	public void add(MonthHyperlink monthHyperlink) {
		this.getChildren().add(monthHyperlink);
	}
}
