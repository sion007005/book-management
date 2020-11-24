package sion.bookmanagement.util.validator;

import java.util.Objects;

public class NoStringValueValidator {
	public static boolean validate(String content) {
		if (Objects.isNull(content)) {
			return true;
		}
		
		if (content.equals("") || content.length() == 0) {
			return true;
		}
		
		return false;
	}
}
