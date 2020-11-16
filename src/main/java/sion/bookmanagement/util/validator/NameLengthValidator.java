package sion.bookmanagement.util.validator;

public class NameLengthValidator extends StringLengthValidator {

	@Override
	public void validate(String name) {
		try {
			if(name.length() > 20) {
				throw new ValidateException("올바르지 않은 이름 길이");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생!", e);
		}
	}

}
