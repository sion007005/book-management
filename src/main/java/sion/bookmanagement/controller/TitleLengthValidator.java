package sion.bookmanagement.controller;

import sion.bookmanagement.util.validator.StringLengthValidator;
import sion.bookmanagement.util.validator.ValidateException;

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
