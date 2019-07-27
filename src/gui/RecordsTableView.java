package gui;

import data.Record;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordsTableView extends TableView<Record> {

	TableColumn<Record, Integer> idColumn = new TableColumn<Record, Integer>("id");
	TableColumn<Record, String> descriptionColumn = new TableColumn<Record, String>("description");
	TableColumn<Record, Double> valueColumn = new TableColumn<Record, Double>("value");
	TableColumn<Record, String> walletColumn = new TableColumn<Record, String>("wallet");

	public RecordsTableView() {
		super();
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("Id"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("Description"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Record, Double>("Value"));
		walletColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("Wallet"));
		
		idColumn.prefWidthProperty().bind(this.widthProperty().divide(4));
		descriptionColumn.prefWidthProperty().bind(this.widthProperty().divide(4));
		valueColumn.prefWidthProperty().bind(this.widthProperty().divide(4));
		walletColumn.prefWidthProperty().bind(this.widthProperty().divide(4));
		
		this.getColumns().add(idColumn);
		this.getColumns().add(descriptionColumn);
		this.getColumns().add(valueColumn);
		this.getColumns().add(walletColumn);

	}

	public void add(Record data) {
		this.getItems().add(data);
	}
}
