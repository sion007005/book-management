package sion.bookmanagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {
	public static java.util.Date getDate(String date) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date created = null;
		
		try {
			created = fm.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return created;
	}
	
	public static java.sql.Timestamp getTimestamp(java.util.Date date) {
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
		return sqlDate;
	}
}
