package sion.bookmanagement.util.validator;
import java.util.regex.Pattern;

public class EmailValidator {
	public boolean validate(String content) {
		try {
			if(!Pattern.matches("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i", content)) {
				return false;
//				throw new ValidateException("올바르지 않은 이메일 형식");
			}
		} catch(Exception e) {
			throw new ValidateException(e.getMessage(), e);
		}
		return true;
	}
}
