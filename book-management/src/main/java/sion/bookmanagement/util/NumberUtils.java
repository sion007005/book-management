package sion.bookmanagement.util;

public class NumberUtils {
	public static Integer parseInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Integer parseInt(String value, Integer defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
