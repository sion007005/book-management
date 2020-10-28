package sion.mvc;

public class HttpResponse<T> {
	public static final String REDIRECT_NAME = "redirect:";
	
	private int statusCode;
	private T body;//json이 될 수도 있고, html이 될 수도 있음
	private String viewName;
	private String redirectPath;
	
	public HttpResponse(T body, String viewName) {	
		this.body = body;
		this.viewName = viewName;
		makeStatusCode();
		makeRedirectPath();
	} 
	
	private void makeRedirectPath() {
		this.redirectPath = this.viewName.replace(REDIRECT_NAME, "");
	}

	private boolean makeStatusCode() {
		if (viewName == null) {
			return false;
		}
		
		if (viewName.startsWith(REDIRECT_NAME)) {
			this.statusCode = 302;
			return true;
		} 			
		
		this.statusCode = 200; 
		return false;
	}

	public int getStatusCode() {
		return this.statusCode;
	}
	
	public T getBody() {
		return body;
	}
	
	public String getViewName() {
		return viewName;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
