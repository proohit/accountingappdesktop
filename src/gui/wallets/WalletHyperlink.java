package gui.wallets;

import data.Wallet;
import gui.Ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

public class WalletHyperlink extends Hyperlink {

	public WalletHyperlink(Wallet wallet) {
		super(wallet.getName() + "(" + wallet.getBalance() + ")");
		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Ui.records.refreshForWallet(wallet.getName());
			}
		});
	}

	public void updateText(String text) {
		this.setText(text);
	}
}
