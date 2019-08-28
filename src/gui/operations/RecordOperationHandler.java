package gui.operations;

import java.sql.SQLException;

import DBTables.RecordTable;
import DBTables.WalletTable;
import data.Record;
import data.Wallet;
import gui.Ui;
import gui.operations.buttonHandler.AddClickHandler;
import gui.operations.buttonHandler.DeleteClickHandler;
import gui.operations.buttonHandler.EditClickHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class RecordOperationHandler {

	static GridPane operationPane = new GridPane();
	static Label descriptionLabel = new Label("description");
	static TextField descriptionField = new TextField();
	static Label valueLabel = new Label("value");
	static TextField valueField = new TextField();
	static Label walletLabel = new Label("wallet");
	static ComboBox<Wallet> walletList = new ComboBox<Wallet>();

	static Label delIdLabel = new Label("record id");
	static TextField delIdField = new TextField();

	private static void initializeGrid() {

		operationPane = new GridPane();
		descriptionField = new TextField();
		valueField = new TextField();
		delIdField = new TextField();
		operationPane.setPrefSize(300, 150);
		operationPane.setPadding(new Insets(0, 10, 0, 10));
		operationPane.setHgap(15);
	}

	public static Stage showAddWindow() {
		initializeGrid();
		GridPane.setHgrow(descriptionField, Priority.SOMETIMES);

		final Stage addWindow = new Stage();

		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addWindow.hide();
				walletList.getItems().clear();
			}
		});

		Button addButton = new Button("add");
		addButton.setOnAction(new AddClickHandler(addWindow));

		fillWalletList();

		operationPane.add(descriptionLabel, 0, 0);
		operationPane.add(descriptionField, 1, 0);
		operationPane.add(valueLabel, 0, 1);
		operationPane.add(valueField, 1, 1);
		operationPane.add(walletLabel, 0, 2);
		operationPane.add(walletList, 1, 2);
		operationPane.add(addButton, 0, 3);
		operationPane.add(cancelButton, 1, 3);

		Scene addScene = new Scene(operationPane);
		addWindow.setScene(addScene);
		addWindow.setTitle("create a new record");
		return addWindow;
	}

	public static Stage showEditWindow() {
		initializeGrid();
		if (Ui.records.getSelectionModel().getSelectedItem() != null) {
			
			final Record rec = Ui.records.getSelectionModel().getSelectedItem();
			Stage editRecordWindow = new Stage();
			GridPane temp = operationPane;
			operationPane = new GridPane();
			operationPane.setPrefSize(300, 150);
			operationPane.setPadding(new Insets(0, 10, 0, 10));
			operationPane.setHgap(15);

			descriptionField.setText(rec.getDescription());
			valueField.setText(Double.toString(rec.getValue()));
			fillWalletList();
			walletList.setValue(WalletTable.getWalletByName(rec.getWallet()));

			operationPane.add(descriptionLabel, 0, 0);
			operationPane.add(descriptionField, 1, 0);
			operationPane.add(valueLabel, 0, 1);
			operationPane.add(valueField, 1, 1);
			operationPane.add(walletLabel, 0, 2);
			operationPane.add(walletList, 1, 2);

			Scene editRecordScene = new Scene(operationPane);

			Button confirmButton = new Button("confirm");
			confirmButton.setOnAction(new EditClickHandler(editRecordWindow, rec));
			Button cancelButton = new Button("cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					operationPane = temp;
					editRecordWindow.hide();
					Ui.records.getSelectionModel().clearSelection();
				}
			});

			operationPane.add(confirmButton, 0, 3);
			operationPane.add(cancelButton, 1, 3);

			editRecordWindow.setScene(editRecordScene);
			return editRecordWindow;
		} else {
			final Stage idEditWindow = new Stage();
			Button okButton = new Button("ok");
			okButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					try {
						final Record rec = RecordTable.getById(getDelId());

						idEditWindow.hide();

						Stage editRecordWindow = new Stage();
						GridPane temp = operationPane;
						operationPane = new GridPane();
						operationPane.setPrefSize(300, 150);
						operationPane.setPadding(new Insets(0, 10, 0, 10));
						operationPane.setHgap(15);

						descriptionField.setText(rec.getDescription());
						valueField.setText(Double.toString(rec.getValue()));
						fillWalletList();
						walletList.setValue(WalletTable.getWalletByName(rec.getWallet()));

						operationPane.add(descriptionLabel, 0, 0);
						operationPane.add(descriptionField, 1, 0);
						operationPane.add(valueLabel, 0, 1);
						operationPane.add(valueField, 1, 1);
						operationPane.add(walletLabel, 0, 2);
						operationPane.add(walletList, 1, 2);

						Scene editRecordScene = new Scene(operationPane);

						Button confirmButton = new Button("confirm");
						confirmButton.setOnAction(new EditClickHandler(editRecordWindow, rec));
						Button cancelButton = new Button("cancel");
						cancelButton.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								operationPane = temp;
								editRecordWindow.hide();
								idEditWindow.show();
								walletList.getItems().clear();
							}
						});

						operationPane.add(confirmButton, 0, 3);
						operationPane.add(cancelButton, 1, 3);

						editRecordWindow.setScene(editRecordScene);
						editRecordWindow.show();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});

			Button cancelButton = new Button("cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					idEditWindow.hide();
				}
			});
			operationPane.add(delIdLabel, 0, 0);
			operationPane.add(delIdField, 1, 0);
			operationPane.add(okButton, 0, 1);
			operationPane.add(cancelButton, 1, 1);
			Scene addScene = new Scene(operationPane);

			idEditWindow.setScene(addScene);

			return idEditWindow;
		}
	}

	public static Stage deleteRecordWindow() {
		if (Ui.records.getSelectionModel().getSelectedItem() != null) {
			Record rec = Ui.records.getSelectionModel().getSelectedItem();
			Stage confirmStage = new Stage();
			GridPane operationPane = new GridPane();
			operationPane.setPrefSize(360, 100);
			operationPane.setPadding(new Insets(0, 10, 0, 10));
			operationPane.setHgap(15);

			Label confirmLabel = new Label();
			confirmLabel.setText("Are you sure you want to delete \n" + rec.toString());
			Button okButton = new Button("ok");
			Button cancelButton = new Button("cancel");

			okButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					try {
						confirmStage.hide();
						RecordTable.deleteById(rec.getId());
						Ui.wallets.refreshAll();
						Ui.records.refreshForMonth(rec.getYear() + "-" + rec.getMonth());
					} catch (Exception e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setContentText(e.getMessage());
						error.show();
					}
				}
			});
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					confirmStage.hide();
				}
			});

			operationPane.add(confirmLabel, 1, 0);
			operationPane.add(okButton, 0, 1);
			operationPane.add(cancelButton, 2, 1);

			Scene confirmScene = new Scene(operationPane);
			confirmStage.setScene(confirmScene);
			return confirmStage;
		}
		initializeGrid();
		GridPane.setHgrow(delIdField, Priority.SOMETIMES);
		final Stage deleteWindow = new Stage();

		Button deleteButton = new Button("delete");
		deleteButton.setOnAction(new DeleteClickHandler(deleteWindow));

		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteWindow.hide();
			}
		});

		operationPane.add(delIdLabel, 0, 0);
		operationPane.add(delIdField, 1, 0);
		operationPane.add(deleteButton, 0, 1);
		operationPane.add(cancelButton, 1, 1);

		Scene addScene = new Scene(operationPane);
		deleteWindow.setScene(addScene);
		deleteWindow.setTitle("delete a record");
		return deleteWindow;
	}

	private static void fillWalletList() {
		walletList.getItems().clear();
		WalletTable.getWallets().stream().forEach(wallet -> {
			walletList.getItems().add(wallet);
		});
	}

	public static String getDescriptionField() {
		return descriptionField.getText();
	}

	public static double getValueField() {
		return Double.parseDouble(valueField.getText());
	}

	public static String getWalletField() {
		return walletList.getValue().getName();
	}

	public static int getDelId() {
		return Integer.parseInt(delIdField.getText());
	}
	
	public static ComboBox<Wallet> getWalletList() {
		return walletList;
	}
}
