package sion.bookmanagement.util.validator;

public class PhoneLengthValidator extends StringLengthValidator {

	@Override
	public void validate(String phoneNumber) {
		try {
			if(phoneNumber.length() >= 13) {
				throw new ValidateException("올바르지 않은 휴대폰 번호 길이");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생!", e);
		}
	}
}
