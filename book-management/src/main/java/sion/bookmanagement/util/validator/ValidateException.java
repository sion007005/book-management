package sion.bookmanagement.util.validator;

public class ValidateException extends RuntimeException {
	
	public ValidateException(Throwable t) {
		super(t);
	}
	
	public ValidateException(String errorMessage) {
		super(errorMessage);
	}
	
	public ValidateException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}	
}
