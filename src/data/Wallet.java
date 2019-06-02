package data;

public class Wallet {
	String name;
	double balance;

	public Wallet(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public double updateBalance(double update) {
		balance += update;
		return balance;
	}
	public String toString() {
		return name + " " + balance;
	}
}
