package sion.bookmanagement.util.validator;
import java.util.regex.Pattern;

/**
 * 한글로만 혹은 영어로만 이루어져있고, 영어 이름은 공백을 허용한다.
 * @author Sion
 */
public class NameValidator implements Validator<String> {
	private HasValueValidator hasValueValidator = new HasValueValidator();
	private NameLengthValidator nameLengthValidator = new NameLengthValidator();
	
	@Override
	public void validate(String content) {
		hasValueValidator.validate(content, "name");
		nameLengthValidator.validate(content);
		
		try {
			if(!Pattern.matches("^[가-힣]+$|^[a-zA-Z\\s]+$", content)) {
				throw new ValidateException("올바르지 않은 이름 입력");
			}
		} catch(Exception e) {
			throw new ValidateException("Exception 발생! 이유는 찾아봐", e);
		}
	}
}
