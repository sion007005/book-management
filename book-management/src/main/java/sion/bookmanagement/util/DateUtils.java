package sion.bookmanagement.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static java.util.Date getDate(String date) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date created = null;
		try {
			created = fm.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return created;
	}
	
	public static Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
}
