package gui.operations;

import java.sql.SQLException;

import DBTables.WalletTable;
import data.Wallet;
import gui.Ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WalletOperationHandler {
	static GridPane operationPane;
	static Label walletNameLabel = new Label("Wallet Name");
	static TextField walletNameField;
	static Label balanceLabel = new Label("initial balance");
	static TextField balanceField;
	static ComboBox<Wallet> walletList = new ComboBox<Wallet>();

	private static void initializeGrid() {
		operationPane = new GridPane();
		walletNameField = new TextField();
		balanceField = new TextField();
		operationPane.setPrefSize(300, 150);
		operationPane.setPadding(new Insets(0, 10, 0, 10));
		operationPane.setHgap(15);
	}

	public static Stage addWindow() {
		initializeGrid();
		final Stage addWindow = new Stage();

		Button okButton = new Button("add");
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					WalletTable.insertValue(getWalletName(), getWalletBalance());
					Ui.wallets.refreshAll();
					addWindow.hide();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addWindow.hide();
			}
		});
		operationPane.add(walletNameLabel, 0, 0);
		operationPane.add(walletNameField, 1, 0);
		operationPane.add(balanceLabel, 0, 1);
		operationPane.add(balanceField, 1, 1);
		operationPane.add(okButton, 0, 2);
		operationPane.add(cancelButton, 1, 2);
		Scene addScene = new Scene(operationPane);
		addWindow.setScene(addScene);
		return addWindow;
	}

	public static Stage showEditWindow() {
		initializeGrid();
		final Stage nameEditWindow = new Stage();

		Button okButton = new Button("ok");
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				nameEditWindow.hide();
				final Stage editWindow = new Stage();
				GridPane temp = operationPane;
				operationPane = new GridPane();
				operationPane.setPrefSize(300, 150);
				operationPane.setPadding(new Insets(0, 10, 0, 10));
				operationPane.setHgap(15);
				walletNameField.setText(walletList.getValue().getName());
				walletNameField.setEditable(false);
				balanceField.setText(Double.toString(walletList.getValue().getBalance()));
				
				Button editOkButton = new Button("confirm");
				editOkButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						WalletTable.setBalance(getWalletName(), getWalletBalance());
						editWindow.hide();
						nameEditWindow.hide();
						Ui.wallets.refreshAll();
					}
				});
				Button editCancelButton = new Button("cancel");
				editCancelButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						operationPane = temp;
						editWindow.hide();
						nameEditWindow.show();
					}
				});
				operationPane.add(walletNameLabel, 0, 0);
				operationPane.add(walletNameField, 1, 0);
				operationPane.add(balanceLabel, 0, 1);
				operationPane.add(balanceField, 1, 1);
				operationPane.add(editOkButton, 0, 2);
				operationPane.add(editCancelButton, 1, 2);

				Scene editScene = new Scene(operationPane);
				editWindow.setScene(editScene);
				editWindow.show();
			}
		});

		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				nameEditWindow.hide();
			}
		});
		
		fillWalletList();
		operationPane.add(walletNameLabel, 0, 0);
		operationPane.add(walletList, 1, 0);
		operationPane.add(okButton, 0, 1);
		operationPane.add(cancelButton, 1, 1);

		Scene nameEditScene = new Scene(operationPane);
		nameEditWindow.setScene(nameEditScene);
		return nameEditWindow;
	}

	private static void fillWalletList() {
		walletList.getItems().clear();
		WalletTable.getWallets().stream().forEach(wallet -> {
			walletList.getItems().add(wallet);
		});
	}

	public static String getWalletName() {
		return walletNameField.getText();
	}

	public static double getWalletBalance() {
		return Double.parseDouble(balanceField.getText());
	}

}
