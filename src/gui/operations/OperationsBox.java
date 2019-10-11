package gui.operations;

import DBTables.RecordTable;
import data.Record;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class OperationsBox extends VBox {
    private static TableView<Record> evalTable;
	private static Label sumLabel = new Label("total difference: ");

    public OperationsBox() {
        super();
        Label operationsLabel = new Label("Operations");
        operationsLabel.setStyle("-fx-font-size: 14px;");
        this.add(operationsLabel);
        this.setStyle("-fx-padding: 10,0,0,0;");

        Separator separator1 = new Separator();
        this.getChildren().add(separator1);

        Label recordOperations = new Label("Record operations");
        recordOperations.setStyle("-fx-font-size: 14px");
        this.add(recordOperations);

        this.add(new OperationHyperlink("add record..", "addRecord"));
        this.add(new OperationHyperlink("edit record..", "editRecord"));
        this.add(new OperationHyperlink("delete record..", "deleteRecord"));

        Separator separator2 = new Separator();
        this.getChildren().add(separator2);

        Label walletOperations = new Label("Wallet operations");
        walletOperations.setStyle("-fx-font-size: 14px");
        this.add(walletOperations);

        this.add(new OperationHyperlink("add wallet...", "addWallet"));
        this.add(new OperationHyperlink("edit wallet...", "editWallet"));
        this.setAlignment(Pos.TOP_CENTER);

        Separator seperator3 = new Separator();
        this.getChildren().add(seperator3);

//        this.add(new OperationHyperlink("evaluate month", "evalMonth"));
		this.getChildren().add(new Label("evaluation Table"));
		this.evalTable = initiateEvalTable();
		this.getChildren().add(evalTable);
		this.getChildren().add(sumLabel);


	}

    public void add(Label label) {
        this.getChildren().add(label);
    }

    public void add(OperationHyperlink link) {
        this.getChildren().add(link);
    }

    public TableView<Record> initiateEvalTable() {
        TableColumn<Record, String> descriptionCol = new TableColumn<>("description");
        TableColumn<Record, Double> valueCol = new TableColumn<>("value");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Record, String>("description"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Record, Double>("Value"));

        TableView<Record> evalTable = new TableView<>();
		evalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		evalTable.getColumns().addAll(descriptionCol, valueCol);

        return evalTable;
    }

    public static void updateEvalTable(String month) {
        try {
        	evalTable.getItems().clear();
            List<Record> result = RecordTable.getEvaluationOfMonth(month);
            result.forEach((record) -> {
                evalTable.getItems().add(record);
            });

			double monthlySum = RecordTable.getSumOfMonth(month);
			sumLabel.setText("total difference: " + Double.toString(monthlySum));
			if(monthlySum>0) {
				sumLabel.setStyle("-fx-background-color: greenyellow");
			}else {
				sumLabel.setStyle("-fx-background-color: red");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
