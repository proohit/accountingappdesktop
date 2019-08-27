package gui.records;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBManager;
import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.search.CenterTopAnchor;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

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
		this.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				if(dragEvent.getDragboard().hasFiles()) {
					dragEvent.acceptTransferModes(TransferMode.ANY);
				}
			}
		});
		this.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				File draggedFile = dragEvent.getDragboard().getFiles().get(0);
				if (draggedFile != null) {
					DBManager.createDB(draggedFile.getAbsolutePath());
					Ui.months.refreshAll();
					Ui.wallets.refreshAll();
					Ui.records.clear();
					((Stage) getScene().getWindow()).setTitle("Accounting App - " + draggedFile.getName());
					Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
					confirmation.setContentText("the database has been loaded from " + draggedFile.getAbsolutePath());
					confirmation.show();
					dragEvent.setDropCompleted(true);
					dragEvent.consume();
					confirmation.setOnHidden(new EventHandler<DialogEvent>() {
						@Override
						public void handle(DialogEvent event) {
							if(!Ui.configurator.isDefault(draggedFile.getAbsolutePath())) {
								ButtonType yes = new ButtonType("yes");
								ButtonType no = new ButtonType("no");
								Alert confirmation = new Alert(Alert.AlertType.NONE, "would you like to save this database as default? \n" +
										"everytime you open the app, this database will be opened", yes, no);
								confirmation.showAndWait().ifPresent(result -> {
									if(result == yes) {
										Ui.configurator.writeEntry("defaultDb=" + draggedFile.getAbsolutePath());
									} else if(result == no) {
										confirmation.close();
									}
								});
							}
						}
					});
				}
			}
		});
	}

	public void refreshForMonth(String month) {
		try {
			this.clear();
			CenterTopAnchor.changeCurrentView(month);
			ArrayList<Record> tempItemList = RecordTable.getByMonth(month);
			tempItemList.stream().forEach(monthItem -> {
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
			CenterTopAnchor.changeCurrentView(wallet);
			ArrayList<Record> tempItemList = RecordTable.getByWallet(wallet);
			tempItemList.stream().forEach(walletItem -> {
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
