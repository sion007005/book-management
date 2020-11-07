package sion.mvc;

public class HttpResponse {
	public static final String REDIRECT_NAME = "redirect:";
	
	private HttpStatus httpStatus;
	private Model model;
	private String viewName;
	private String redirectPath;
	
	public HttpResponse(Model model, String viewName) {	
		this.model = model;
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
			this.httpStatus = HttpStatus.MOVED_PERMANENTLY;
			return true;
		} 			
		
		this.httpStatus = HttpStatus.OK; 
		return false;
	}

	public int getStatusCode() {
		return this.httpStatus.getCode();
	}
	
	public String getViewName() {
		return viewName;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public Model getModel() {
		return model;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
