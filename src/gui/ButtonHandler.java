package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class ButtonHandler {

	public Stage showAddWindow() {
		GridPane addPane = new GridPane();
		Label descriptionLabel = new Label("description");
		TextField descriptionField = new TextField();
		Label valueLabel = new Label("value");
		TextField valueField = new TextField();
		Label walletLabel = new Label("wallet");
		TextField walletField = new TextField();
		
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
	public Stage showEditWindow() {
		GridPane addPane = new GridPane();
		Label descriptionLabel = new Label("description");
		TextField descriptionField = new TextField();
		Label valueLabel = new Label("value");
		TextField valueField = new TextField();
		Label walletLabel = new Label("wallet");
		TextField walletField = new TextField();
		
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
}