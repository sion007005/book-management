package sion.bookmanagement.util.validator;
import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
	private HasValueValidator hasValueValidator = new HasValueValidator();
	
	@Override
	public void validate(String content) {
		hasValueValidator.validate(content, "email");
		
		try {
			if(!Pattern.matches("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$", content)) {
				throw new ValidateException("올바르지 않은 이메일 형식");
			}
		} catch(Exception e) {
			throw new ValidateException("Exception 발생! 이유는 찾아봐", e);
		}
	}
}
