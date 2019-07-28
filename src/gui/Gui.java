package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import DBConnection.DBManager;
import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gui extends Application {
	RecordTable recordTable = new RecordTable();
	WalletTable walletTable = new WalletTable();
	ListView<String> list;
	GridPane recordsGrid;
	GridPane border;
	VBox months;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		DBManager.createDB("Accounting");
		javafx.scene.control.ScrollPane spPane = new javafx.scene.control.ScrollPane();
		border = new GridPane();
		recordsGrid = new GridPane();
		initiateRecordGrid();

		stage.setTitle("Accounting App");
		stage.setScene(new Scene(spPane));

//		Label wallets = new Label();
//		walletTable.getWallets().stream().forEach(wallet -> wallets.setText(wallets.getText() + wallet.toString()));
		spPane.setContent(border);
		spPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		months = addMonths();
		border.add(months, 0, 0);
//		border.add(addButton(), 2, 1);
//		border.add(editButton(), 3, 1);
		border.add(recordsGrid, 1, 0);

		stage.show();
	}

	private Button editButton() {
		Button editButton = new Button("edit Record");
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GridPane editGridPane = new GridPane();
				Label idLabel = new Label("record id");
				TextField idField = new TextField();
				editGridPane.add(idLabel, 0, 0);
				editGridPane.add(idField, 1, 0);

				Scene editScene = new Scene(editGridPane);
				Stage editStage = new Stage();
				editStage.initModality(Modality.APPLICATION_MODAL);
				editStage.setScene(editScene);
				editStage.setTitle("enter record id");

				Button okButton = new Button("OK");
				Button cancelButton = new Button("Cancel");
				editGridPane.add(okButton, 0, 1);
				editGridPane.add(cancelButton, 1, 1);

				okButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
							Record record = RecordTable.getById(Integer.parseInt(idField.getText()));

							GridPane addPane = new GridPane();
							Label descriptionLabel = new Label("description");
							TextField descriptionField = new TextField(record.getDescription());
							Label valueLabel = new Label("value");
							TextField valueField = new TextField(Double.toString(record.getValue()));
							Label walletLabel = new Label("wallet");
							TextField walletField = new TextField(record.getWallet());
							addPane.add(descriptionLabel, 0, 0);
							addPane.add(descriptionField, 1, 0);
							addPane.add(valueLabel, 0, 1);
							addPane.add(valueField, 1, 1);
							addPane.add(walletLabel, 0, 2);
							addPane.add(walletField, 1, 2);

							Button okButton = new Button("OK");

							Button cancelButton = new Button("Cancel");
							cancelButton.setCancelButton(true);
							addPane.add(okButton, 0, 3);
							addPane.add(cancelButton, 1, 3);

							Scene addScene = new Scene(addPane);
							Stage addWindow = new Stage();
							addWindow.setTitle("edit a record");
							addWindow.initModality(Modality.APPLICATION_MODAL);
							addWindow.setScene(addScene);

							okButton.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									try {
										RecordTable.updateRecord(record, Double.parseDouble(valueField.getText()),
												descriptionField.getText(), walletField.getText());
										reloadWindow();
										addWindow.hide();
										editStage.hide();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							cancelButton.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									addWindow.hide();
								}
							});

							addWindow.show();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				cancelButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						editStage.hide();
					}
				});

				editStage.show();
			}
		});

		return editButton;
	}


	private void initiateRecordGrid() {

		Background grayBg = new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTGRAY, CornerRadii.EMPTY,
				javafx.geometry.Insets.EMPTY));

		Label idLabel = new Label("recordId");
		idLabel.setBackground(grayBg);
		Label description = new Label("description");
		description.setBackground(grayBg);
		Label value = new Label("value");
		value.setBackground(grayBg);
		
		recordsGrid.setHgap(15);
		recordsGrid.setPadding(new Insets(10, 50, 50, 50));

		recordsGrid.add(idLabel, 0, 0);
		recordsGrid.add(value, 2, 0);
		recordsGrid.add(description, 1, 0);

		recordsGrid.setBackground(grayBg);

	}

	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}
	private void reloadWindow() {
		try {
		border.getChildren().remove(getNodeFromGridPane(border, 0, 0));
		months = addMonths();
		border.add(months, 0, 0);
		border.getChildren().remove(getNodeFromGridPane(border, 1, 1));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private VBox addMonths() {
		try {
			VBox vbox = new VBox();

			vbox.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLUE, CornerRadii.EMPTY,
					javafx.geometry.Insets.EMPTY)));
			Text title = new Text("Months");
			vbox.getChildren().add(title);
			ArrayList<String> months = RecordTable.getMonths();
			months.stream().forEachOrdered(month -> {
				Hyperlink link = new Hyperlink(month);
				link.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTGRAY,
						CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

				link.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						try {
							recordsGrid.getChildren().clear();
							initiateRecordGrid();
							ArrayList<Record> records = RecordTable.getByMonth(link.getText());
							for (int i = 0; i < records.size(); i++) {
								Record item = records.get(i);
								Label idLabel = new Label(Integer.toString(item.getId()));
								Label description = new Label(item.getDescription());
								Label value = new Label(Double.toString(item.getValue()));
								recordsGrid.add(idLabel, 0, i + 1);
								recordsGrid.add(value, 2, i + 1);
								recordsGrid.add(description, 1, i + 1);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				vbox.getChildren().add(link);
			});

			return vbox;
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		launch();
	}

}
