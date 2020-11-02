package sion.mvc.dispatcher;

public class FileNotFoundException extends RuntimeException {

	public FileNotFoundException(Throwable t) {
		super(t);
	}
	
	public FileNotFoundException(String message) {
		super(message);
	}
	
	public FileNotFoundException(String message, Throwable t) {
		super(message, t);
	}
	
}
