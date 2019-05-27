package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Record {
	// Datum funktioniert noch nicht
	Calendar timestamp;
	
	int recordId;
	String description;
	double value;
	String walletName;

	public Record(String description, double value, String walletName) {
		this.description = description;
		this.walletName = walletName;
		this.value = value;
	}

	public Record(String timestamp, int recordId, String description, double value, String walletName) {
		try {
			this.timestamp = parseDate(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.description = description;
		this.recordId = recordId;
		this.walletName = walletName;
		this.value = value;
	}

	public Calendar parseDate(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd-kk:mm:ss");
		timestamp = Calendar.getInstance();
		timestamp.setTimeZone(TimeZone.getDefault());
		timestamp.setTime(format1.parse(date));
		int offset = timestamp.get(Calendar.ZONE_OFFSET)/1000/60/60;
		
		timestamp.set(Calendar.HOUR, getHour()+ timestamp.get(Calendar.ZONE_OFFSET)/1000/60/60);
		return timestamp;
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

	private String getTimestamp() {
		return getYear() + "." + getMonth() + "." + getDay() + "-" + getHour() + ":" + getMinutes() + ":"
				+ getSeconds() + " " + (timestamp.get(Calendar.AM_PM) == 1 ? "PM":"AM");
	}

	public void print() {
		System.out.println(toString());
	}

	public int getYear() {
		return timestamp.get(Calendar.YEAR);
	}

	public int getMonth() {
		return timestamp.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		return timestamp.get(Calendar.DATE);
	}

	public int getHour() {
		return timestamp.get(Calendar.HOUR);
	}

	public int getMinutes() {
		return timestamp.get(Calendar.MINUTE);
	}

	public int getSeconds() {
		return timestamp.get(Calendar.SECOND);
	}

	public String toString() {
		return recordId + " " + description + " " + walletName + " " + value + " " + getTimestamp();
	}
}
