package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddButton extends ButtonHandler{
	Button addButton;

	AddButton(String text) {
		addButton = new Button(text);
	}
	
	public void add() {
		
		showAddWindow().show();
	}
}
