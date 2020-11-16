package sion.http;

import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import sion.mvc.dispatcher.DispatcherException;
import sion.mvc.support.HttpUtils;

public class HttpRequest {
	private String uriPath;
	private String method;
	private HttpExchange httpExchange;
	private Map<String, Object> parameters;
	private Map<String, Object> attributes;
	
	public HttpRequest(HttpExchange httpExchange) {
		try {
			this.httpExchange = httpExchange;
			this.uriPath = HttpUtils.makeUriPath(httpExchange);
			this.method = httpExchange.getRequestMethod();
			this.parameters = HttpUtils.makeParameters(httpExchange);
			this.attributes = HttpUtils.makeAttributes(httpExchange);

			validate();
		} catch (Exception e) {
			throw new DispatcherException(e);
		}
	}

	private void validate() {
		if(uriPath == null || uriPath.length() == 0) {
			throw new RuntimeException("url 값이 없습니다.");
		}
		
		if(method == null || method.length() == 0) {
			throw new RuntimeException("method 값이 없습니다.");
		}
	}
	
	public HttpRequest(String url, String method, Map<String, Object> parameters, Map<String, Object> attributes) {
		if(url == null || url.length() == 0) {
			throw new RuntimeException("url 값이 없습니다.");
		}
		
		if(method == null || method.length() == 0) {
			throw new RuntimeException("method 값이 없습니다.");
		}
	
		this.uriPath = url;
		this.method = method;
		this.parameters = parameters;
		this.attributes = attributes;
	}
	
	public String getUriPath() {
		return uriPath;
	}

	public String getMethod() {
		return method;
	}

	public Object getParameter(String key) {
		if (parameters == null) {
			return null;
		}
		return parameters.get(key);
	}
	
	public Object getAttribute(String key) {
		if (attributes == null) {
			return null;
		}
		
		return attributes.get(key);
	}
	
	public Headers getHeaders() {
		return httpExchange.getRequestHeaders(); 
	}
	
	public String getAccessIp() {
		return httpExchange.getRemoteAddress().getHostName();
	}
}
