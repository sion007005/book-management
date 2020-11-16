package sion.bookmanagement.repository;


public class DataProcessException extends RuntimeException {

	public DataProcessException(Throwable e) {
		super(e);
	}
	
	public DataProcessException(String message) {
		super(message);
	}
	
	public DataProcessException(String message, Throwable e) {
		super(message, e);
	}
}
