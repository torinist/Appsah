package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Dateutil {

	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

//	public static Date getNowDate() {
	public static ZonedDateTime getNowDate() {
		ZonedDateTime zoneDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		return zoneDateTime;
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), new Locale("jp", "JP", "JP"));
//		System.out.println("今のお時間は" + calendar.getTime());
//		return calendar.getTime();
	}

//	public static String dateToString(Date date) {
	public static String dateToString(ZonedDateTime zdt) {
//		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//		return sdf.format(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, new Locale("ja", "JP", "JP"));
		return zdt.format(formatter);
	}

	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.parse(date);
	}

}
