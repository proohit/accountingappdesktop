package gui.wallets;

import java.util.ArrayList;

import data.Wallet;
import DBTables.WalletTable;
import javafx.scene.layout.HBox;

public class WalletBox extends HBox {
	public WalletBox() {
		super();
		this.setStyle("-fx-padding: 10,0,0,10;");
	}
	
	public void refreshAll() {
		try {
			clear();
			ArrayList<Wallet> wallets = WalletTable.getWallets();
			wallets.stream().forEach(wallet -> {
				WalletHyperlink walletLink = new WalletHyperlink(wallet);
				this.add(walletLink);
			});
		} catch (Exception e) {
		}
	}
	
	public void clear() {
		this.getChildren().clear();
	}
	
	public void add(WalletHyperlink wallet) {
		this.getChildren().add(wallet);
	}
}
