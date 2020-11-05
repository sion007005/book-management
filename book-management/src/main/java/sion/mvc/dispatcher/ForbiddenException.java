package sion.mvc.dispatcher;

public class ForbiddenException extends RuntimeException {
	public ForbiddenException(Throwable t) {
		super(t);
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(String message, Throwable t) {
		super(message, t);
	}
}
