package sion.bookmanagement.util.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneLengthValidator extends StringLengthValidator {

	@Override
	public void validate(String phoneNumber) {
		try {
			if(phoneNumber.length() >= 13) {
				log.info("phone: {}", phoneNumber);
				throw new ValidateException("올바르지 않은 휴대폰 번호 길이");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생!", e);
		}
	}
}
