package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Record {
	// Datum funktioniert noch nicht
	Calendar timestamp2;
	String timestamp;
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
			this.timestamp2 = parseDate(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.timestamp=this.getTimestamp();
		this.description = description;
		this.recordId = recordId;
		this.walletName = walletName;
		this.value = value;
	}

	public Calendar parseDate(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timestamp2 = Calendar.getInstance();
		timestamp2.setTimeZone(TimeZone.getDefault());
		timestamp2.setTime(format1.parse(date));
		timestamp2.set(Calendar.HOUR, getHour24()+ timestamp2.get(Calendar.ZONE_OFFSET)/1000/60/60);
		return timestamp2;
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

//	private String getTimestampAmPm() {
//		return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHourAmPm() + ":" + getMinutes() + ":"
//				+ getSeconds() + " " + (timestamp2.get(Calendar.AM_PM) == 1 ? "PM":"AM");
//	}
	public String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(timestamp2.getTime());
	}

	public int getYear() {
		return timestamp2.get(Calendar.YEAR);
	}

	public String getMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(timestamp2.getTime());
	}

	public int getDay() {
		return timestamp2.get(Calendar.DATE);
	}

	public int getHour24() {
		//return timestamp.get(Calendar.AM_PM)==1?timestamp.get(Calendar.HOUR_OF_DAY)+12:timestamp.get(Calendar.HOUR_OF_DAY);
		return timestamp2.get(Calendar.HOUR_OF_DAY);
	}
	public int getHourAmPm() {
		return timestamp2.get(Calendar.HOUR);
	}

	public int getMinutes() {
		return timestamp2.get(Calendar.MINUTE);
	}

	public int getSeconds() {
		return timestamp2.get(Calendar.SECOND);
	}
	public void setId(int id) {
		this.recordId = id;
	}
	@Override
	public String toString() {
		return recordId + " " + description + " " +  value + " " + walletName + " " + getTimestamp();
	}
}
