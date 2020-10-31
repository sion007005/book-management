package sion.mvc;

public class HttpResponse {
	public static final String REDIRECT_NAME = "redirect:";
	
	private int statusCode;
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
			this.statusCode = 302;
			return true;
		} 			
		
		this.statusCode = 200; 
		return false;
	}

	public int getStatusCode() {
		return this.statusCode;
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

	public Model getModel() {
		return model;
	}
}
