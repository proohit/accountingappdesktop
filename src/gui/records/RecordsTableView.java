package gui.records;


import data.Record;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordsTableView extends TableView<Record> {
	final int COLUMNS_COUNT = 5;
	TableColumn<Record, Integer> idColumn = new TableColumn<Record, Integer>("id");
	TableColumn<Record, String> descriptionColumn = new TableColumn<Record, String>("description");
	TableColumn<Record, Double> valueColumn = new TableColumn<Record, Double>("value");
	TableColumn<Record, String> walletColumn = new TableColumn<Record, String>("wallet");
	TableColumn<Record, String> timestampColumn = new TableColumn<Record, String>("timestamp");
	
	public RecordsTableView() {
		super();
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("Id"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("Description"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Record, Double>("Value"));
		walletColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("Wallet"));
		timestampColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("Timestamp"));
		
		idColumn.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS_COUNT));
		descriptionColumn.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS_COUNT));
		valueColumn.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS_COUNT));
		walletColumn.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS_COUNT));
		timestampColumn.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS_COUNT));
		
		this.getColumns().add(idColumn);
		this.getColumns().add(descriptionColumn);
		this.getColumns().add(valueColumn);
		this.getColumns().add(walletColumn);
		this.getColumns().add(timestampColumn);
	}

	public void add(Record data) {
		this.getItems().add(data);
	}
	public void clear() {
		this.getItems().clear();
	}
}
