package gui.operations;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class OperationsBox extends VBox {
	public OperationsBox() {
		super();
		Label operationsLabel = new Label("Operations");
		operationsLabel.setStyle("-fx-font-size: 14px;");
		this.add(operationsLabel);
		this.setStyle("-fx-padding: 10,0,0,0;");
		
		Separator separator1 = new Separator();
		this.getChildren().add(separator1);
		
		Label recordOperations= new Label("Record operations");
		recordOperations.setStyle("-fx-font-size: 14px");
		this.add(recordOperations);
		
		this.add(new OperationHyperlink("add record..", "addRecord"));
		this.add(new OperationHyperlink("edit record..", "editRecord"));
		this.add(new OperationHyperlink("delete record..", "deleteRecord"));
		
		Separator separator2 = new Separator();
		this.getChildren().add(separator2);
		
		Label walletOperations= new Label("Wallet operations");
		walletOperations.setStyle("-fx-font-size: 14px");
		this.add(walletOperations);
		
		this.add(new OperationHyperlink("add wallet...", "addWallet"));
		this.add(new OperationHyperlink("edit wallet...", "editWallet"));
		this.setAlignment(Pos.TOP_CENTER);
	}

	public void add(Label label) {
		this.getChildren().add(label);
	}

	public void add(OperationHyperlink link) {
		this.getChildren().add(link);
	}
}
