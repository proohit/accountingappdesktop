package gui.records;

import java.sql.SQLException;
import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordsTableView extends TableView<Record> {
	final int COLUMNS_COUNT = 5;
	private TableColumn<Record, Integer> idColumn = new TableColumn<Record, Integer>("id");
	private TableColumn<Record, String> descriptionColumn = new TableColumn<Record, String>("description");
	private TableColumn<Record, Double> valueColumn = new TableColumn<Record, Double>("value");
	private TableColumn<Record, String> walletColumn = new TableColumn<Record, String>("wallet");
	private TableColumn<Record, String> timestampColumn = new TableColumn<Record, String>("timestamp");

	private ArrayList<Record> currentItems = new ArrayList<Record>();

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

	public void refreshForMonth(String month) {
		try {
			this.clear();
			currentItems = RecordTable.getByMonth(month);
			currentItems.stream().forEach(monthItem -> {
				this.add(monthItem);
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refreshForWallet(String wallet) {

		try {
			this.clear();
			currentItems = RecordTable.getByWallet(wallet);
			currentItems.stream().forEach(walletItem -> {
				this.add(walletItem);
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void add(Record data) {
		this.getItems().add(data);
		currentItems.add(data);
	}

	public void clear() {
		this.getItems().clear();
		currentItems.clear();
	}

	public ArrayList<Record> getCurrentItems() {
		return currentItems;
	}
}
