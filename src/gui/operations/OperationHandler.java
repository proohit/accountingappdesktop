package gui.operations;

import gui.operations.buttonHandler.AddClickHandler;
import gui.operations.buttonHandler.DeleteClickHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class OperationHandler {

	static GridPane operationPane = new GridPane();
	static Label descriptionLabel = new Label("description");
	static TextField descriptionField = new TextField();
	static Label valueLabel = new Label("value");
	static TextField valueField = new TextField();
	static Label walletLabel = new Label("wallet");
	static TextField walletField = new TextField();
	static Label delIdLabel = new Label("record id");
	static TextField delIdField = new TextField();

	private static void initializeGrid() {
		operationPane= new GridPane();
		descriptionField = new TextField();
		valueField = new TextField();
		walletField = new TextField();
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
			}
		});

		Button addButton = new Button("add");
		addButton.setOnAction(new AddClickHandler(addWindow));

		operationPane.add(descriptionLabel, 0, 0);
		operationPane.add(descriptionField, 1, 0);
		operationPane.add(valueLabel, 0, 1);
		operationPane.add(valueField, 1, 1);
		operationPane.add(walletLabel, 0, 2);
		operationPane.add(walletField, 1, 2);
		operationPane.add(addButton, 0, 3);
		operationPane.add(cancelButton, 1, 3);

		Scene addScene = new Scene(operationPane);
		addWindow.setScene(addScene);
		addWindow.setTitle("create a new record");
		return addWindow;
	}

	public static Stage showEditWindow() {
		initializeGrid();
		operationPane.add(descriptionLabel, 0, 0);
		operationPane.add(descriptionField, 1, 0);
		operationPane.add(valueLabel, 0, 1);
		operationPane.add(valueField, 1, 1);
		operationPane.add(walletLabel, 0, 2);
		operationPane.add(walletField, 1, 2);

		Scene addScene = new Scene(operationPane);
		Stage addWindow = new Stage();
		addWindow.setScene(addScene);

		return addWindow;
	}

	public static Stage deleteRecordWindow() {
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

	public static String getDescriptionField() {
		return descriptionField.getText();
	}

	public static double getValueField() {
		return Double.parseDouble(valueField.getText());
	}

	public static String getWalletField() {
		return walletField.getText();
	}

	public static int getDelId() {
		return Integer.parseInt(delIdField.getText());
	}
}
