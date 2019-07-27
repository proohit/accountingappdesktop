package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HyperlinkEventHandler implements EventHandler<ActionEvent> {

	MonthHyperlink link;
	
	HyperlinkEventHandler(MonthHyperlink link) {
		this.link = link;
	}
	
	public void handle(ActionEvent arg0) {

		try {
			Ui.records.clear();
			ArrayList<Record> records = RecordTable.getByMonth(link.getText());
			records.stream().forEach(month -> {
				Ui.records.add(month);
			});
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
