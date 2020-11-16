package sion.bookmanagement.util.validator;
import java.util.regex.Pattern;

/**
 * F(Female) 혹은 M(Male)만 허용한다.
 * @author Sion
 */
public class GenderValidator implements Validator<String>{
	private HasValueValidator hasValueValidator = new HasValueValidator();
	
	@Override
	public void validate(String content) {
		hasValueValidator.validate(content, "gender");
		
		try {
			if(!Pattern.matches("F|M", content)) {
				throw new ValidateException("올바르지 않은 성별 입력");
			}
		} catch(Exception e) {
			throw new ValidateException("Exception 발생! 이유는 찾아봐", e);
		}
	}
}
