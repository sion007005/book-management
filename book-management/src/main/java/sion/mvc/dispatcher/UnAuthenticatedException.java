package sion.mvc.dispatcher;

public class UnAuthenticatedException extends RuntimeException {
	public UnAuthenticatedException(Throwable t) {
		super(t);
	}
	
	public UnAuthenticatedException(String message) {
		super(message);
	}
	
	public UnAuthenticatedException(String message, Throwable t) {
		super(message, t);
	}
}
