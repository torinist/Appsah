package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Dateutil {

	private static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public static Date getNowDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), new Locale("jp", "JP", "JP"));
		return calendar.getTime();
	}

	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.parse(date);
	}

}
