package data;

import java.sql.Date;

public class Record {
    //Datum funktioniert noch nicht
    String timestamp;
    int recordId;
    String description;
    double value;
    String walletName;

    public Record(String timestamp, int recordId, String description, double value, String walletName) {
	this.timestamp = timestamp;
	this.description = description;
	this.recordId = recordId;
	this.walletName = walletName;
	this.value = value;
    }

    public String getDescription() {
	return description;
    }

    public int getId() {
	return recordId;
    }

    public double getValue() {
	return value;
    }

    public String getWallet() {
	return walletName;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void print() {
	System.out.println(toString());
    }

    public String toString() {
	return recordId + " " + description + " " + walletName + " " + value + " " + timestamp;
    }
}
