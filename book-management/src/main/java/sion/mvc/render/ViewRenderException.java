package sion.mvc.render;

public class ViewRenderException extends RuntimeException {
	public ViewRenderException(Throwable t) {
		super(t);
	}
	
	public ViewRenderException(String message) {
		super(message);
	}
	
	public ViewRenderException(String message, Throwable t) {
		super(message, t);
	}
}
