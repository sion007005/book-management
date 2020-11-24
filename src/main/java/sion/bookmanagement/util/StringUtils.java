package sion.bookmanagement.util;

import java.util.Objects;

public class StringUtils {
	
	public static String trim(String text) {
		if (text == null) {
			return null;
		}
		return text.trim();
	}
	
	public static boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		
		return false;
	}
	
	public static String changeNullToEmpty(String value) {
		if (Objects.isNull(value)) {
			return "";
		}
		
		return value;
	}
}
