package sion.bookmanagement.util;

public class StringUtils {
	
	public static String trim(String text) {
		if (text == null) {
			return null;
		}
		return text.trim();
	}
	
	public static Boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		
		return false;
	}
}
