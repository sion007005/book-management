package sion.http;

public class ServerRunnerException extends RuntimeException {
	
	//생성자는 항상 세개 만든다. 
	public ServerRunnerException(Throwable t) {
		super(t);
	}
	
	public ServerRunnerException(String message) {
		super(message);
	}
	
	public ServerRunnerException(String message, Throwable t) {
		super(message, t);
	}

}
