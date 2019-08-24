package gui.search;

import java.util.ArrayList;

import DBTables.RecordTable;
import data.Record;
import gui.Ui;
import gui.operations.buttonHandler.SearchSettingsClickHandler;
import gui.settings.SearchSettings;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CenterTopAnchor extends AnchorPane {

	HBox searchBar = new HBox(4);
	TextField searchField = new TextField();
	Button gearButton = new Button();


	HBox currentView = new HBox();
	Label currentViewLabel = new Label("Current view: ");
	static Label currentViewItem = new Label();
	static StringProperty currentViewString = new SimpleStringProperty("none");
	public CenterTopAnchor() {
		gearButton.setOnAction(new SearchSettingsClickHandler());
		Image gearIcon = new Image("file:res/gear_icon.png", 20, 20, false, false);
		gearButton.setGraphic(new ImageView(gearIcon));
		gearButton.setTooltip(new Tooltip("configure Search options"));
		searchBar.getChildren().addAll(gearButton, searchField);
		searchBar.setAlignment(Pos.CENTER_RIGHT);
		// search algorithm
		searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					ArrayList<Record> searchList = new ArrayList<Record>();
					if (SearchSettings.isGlobalSearch()) {
						ArrayList<Record> allItems = RecordTable.getAll();
						allItems.stream().forEach(item -> {
							if (item.getDescription().contains(searchField.getText())
									|| Integer.toString(item.getId()).contains(searchField.getText())
									|| item.getTimestamp().contains(searchField.getText())) {
								searchList.add(item);
							}
						});
					} else {
						ArrayList<Record> currentItems = Ui.records.getCurrentItems();
						currentItems.stream().forEach(item -> {
							if (item.getDescription().contains(searchField.getText())
									|| Integer.toString(item.getId()).contains(searchField.getText())
									|| item.getTimestamp().contains(searchField.getText())) {
								searchList.add(item);
							}
						});

					}
					Ui.records.clear();
					searchList.stream().forEach(item -> {
						Ui.records.add(item);
					});
				}
			}
		});
		currentViewItem.textProperty().bind(currentViewString);
		currentView.getChildren().addAll(currentViewLabel, currentViewItem);
		searchField.setPromptText("search records..");

		AnchorPane.setRightAnchor(searchBar, 0.0);
		AnchorPane.setTopAnchor(searchBar, 10.0);

		AnchorPane.setTopAnchor(Ui.records, 40.0);
		AnchorPane.setBottomAnchor(Ui.records, 0.0);
		AnchorPane.setRightAnchor(Ui.records, 0.0);
		AnchorPane.setLeftAnchor(Ui.records, 0.0);
		
		AnchorPane.setLeftAnchor(currentView, 0.0);
		AnchorPane.setTopAnchor(currentView, 10.0);
		
		this.getChildren().add(searchBar);
		this.getChildren().add(Ui.records);
		this.getChildren().add(currentView);

	}
	
	public static void changeCurrentView(String view) {
		currentViewString.set(view);
	}

}
