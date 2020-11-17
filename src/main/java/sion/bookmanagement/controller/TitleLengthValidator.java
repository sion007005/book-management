package sion.bookmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.util.validator.StringLengthValidator;
import sion.bookmanagement.util.validator.ValidateException;
@Slf4j
public class TitleLengthValidator extends StringLengthValidator {
	@Override
	public void validate(String title) {
		try {
			if(title.length() > 100) {
				throw new ValidateException("올바르지 않은 제목 길이");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생!", e);
		}
	}
}
