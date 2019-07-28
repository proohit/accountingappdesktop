package gui.wallets;

import data.Wallet;
import javafx.scene.control.Hyperlink;

public class WalletHyperlink extends Hyperlink {
	public WalletHyperlink(Wallet wallet) {
	
	super(wallet.getName() + "(" + wallet.getBalance() + ")");
	
	
	}
	
	public void updateText(String text) {
		this.setText(text);
	}
}
