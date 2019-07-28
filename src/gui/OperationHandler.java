package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class OperationHandler {

	static GridPane addPane = new GridPane();
	static Label descriptionLabel = new Label("description");
	static TextField descriptionField = new TextField();
	static Label valueLabel = new Label("value");
	static TextField valueField = new TextField();
	static Label walletLabel = new Label("wallet");
	static TextField walletField = new TextField();

	private static void initializeGrid() {
		addPane.setPrefSize(300, 150);
		addPane.setPadding(new Insets(0, 10, 0, 10));
		addPane.setHgap(15);
	}

	public static Stage showAddWindow() {
		initializeGrid();
		AddButton addButton = new AddButton();

		GridPane.setHgrow(descriptionField, Priority.SOMETIMES);

		addPane.add(descriptionLabel, 0, 0);
		addPane.add(descriptionField, 1, 0);
		addPane.add(valueLabel, 0, 1);
		addPane.add(valueField, 1, 1);
		addPane.add(walletLabel, 0, 2);
		addPane.add(walletField, 1, 2);
		addPane.add(addButton, 0, 3);

		Scene addScene = new Scene(addPane);
		Stage addWindow = new Stage();
		addWindow.setScene(addScene);
		addWindow.setTitle("create a new record");
		return addWindow;
	}

	public static Stage showEditWindow() {
		initializeGrid();
		addPane.add(descriptionLabel, 0, 0);
		addPane.add(descriptionField, 1, 0);
		addPane.add(valueLabel, 0, 1);
		addPane.add(valueField, 1, 1);
		addPane.add(walletLabel, 0, 2);
		addPane.add(walletField, 1, 2);

		Scene addScene = new Scene(addPane);
		Stage addWindow = new Stage();
		addWindow.setScene(addScene);

		return addWindow;
	}

	public static Stage deleteRecordWindow() {
		initializeGrid();
		
		Label idLabel = new Label("Record id");
		TextField idField = new TextField();
	}

	public static String getDescriptionField() {
		return descriptionField.getText();
	}

	public static double getValueField() {
		return Integer.parseInt(valueField.getText());
	}

	public static String getWalletField() {
		return walletField.getText();
	}
}
