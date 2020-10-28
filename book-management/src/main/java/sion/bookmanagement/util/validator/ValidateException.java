package sion.bookmanagement.util.validator;

public class ValidateException extends RuntimeException {
	
	public ValidateException() {
	}
	
	public ValidateException(String errorMessage) {
		super(errorMessage);
	}
	
	public ValidateException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}	
}
