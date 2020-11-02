package sion.mvc.dispatcher;

public class DispatcherException extends RuntimeException {

	public DispatcherException(Throwable e) {
		super(e);
	}
	
	public DispatcherException(String message) {
		super(message);
	}
	
	public DispatcherException(String message, Throwable t) {
		super(message, t);
	}
	
}
