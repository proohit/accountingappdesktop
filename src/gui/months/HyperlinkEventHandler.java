package gui.months;

import java.sql.SQLException;
import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HyperlinkEventHandler implements EventHandler<ActionEvent> {

	MonthHyperlink link;

	HyperlinkEventHandler(MonthHyperlink link) {
		this.link = link;
	}

	@Override
	public void handle(ActionEvent arg0) {
			Ui.records.refreshForMonth(link.getText());
	}

}
