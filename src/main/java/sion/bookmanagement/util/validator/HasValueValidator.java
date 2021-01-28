package sion.bookmanagement.util.validator;

import java.util.regex.Pattern;

/**
 * 공백이 아닌 문자값이 하나라도 있는 지 확인
 * @author Sion
 */
public class HasValueValidator {
	public boolean validate(String content, String type) {
		try {
			String trimedContent = content.trim();
			if(!Pattern.matches("^\\S", trimedContent)) {
				return false;
//				throw new ValidateException(type +": 입력된 값이 없음");
			}
		} catch (Exception e) {
			throw new ValidateException(e.getMessage(), e);
		}
		return true;
	}
}
