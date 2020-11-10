package sion.mvc;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class HttpResponse {
	public static final String REDIRECT_NAME = "redirect:";
	
	private HttpStatus httpStatus;
	private HttpExchange httpExchange;
	private ModelAndView modelAndView;
	private String redirectPath;
	
	public HttpResponse(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	} 
	
	private void makeRedirectPath() {
		this.redirectPath = this.modelAndView.getViewName().replace(REDIRECT_NAME, "");
	}

	private boolean makeStatusCode() {
		if (modelAndView.getViewName() == null) {
			return false;
		}
		
		if (modelAndView.getViewName().startsWith(REDIRECT_NAME)) {
			this.httpStatus = HttpStatus.MOVED_PERMANENTLY;
			return true;
		} 			
		
		this.httpStatus = HttpStatus.OK; 
		return false;
	}

	public int getStatusCode() {
		return this.httpStatus.getCode();
	}
	
	public String getRedirectPath() {
		return redirectPath;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public Headers getHeaders() {
		return httpExchange.getResponseHeaders();
	}

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

	public void setModelAndView(ModelAndView modelAndView) {
		this.modelAndView = modelAndView;
		
		makeStatusCode();
		makeRedirectPath();
	}

	public void sendResponseHeaders(int statusCode, int contentLength) throws IOException {
		log.debug("statuscode : {}", statusCode);
		log.debug("contentLength : {}", contentLength);
		log.debug("httpExchange : {}", httpExchange);
		httpExchange.sendResponseHeaders(statusCode, contentLength);
	}

	public OutputStream getResponseBody() {
		return httpExchange.getResponseBody();
	}
}
