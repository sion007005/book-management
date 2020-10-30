package sion.mvc;

import java.util.Map;

public class HttpRequest {
	private String url;
	private String method;
	private Map<String, Object> parameters;
	private Map<String, Object> attributes;
	
	public HttpRequest(String url, String method, Map<String, Object> parameters, Map<String, Object> attributes) {
		if(url == null || url.length() == 0) {
			throw new RuntimeException("url 값이 없습니다.");
		}
		
		if(method == null || method.length() == 0) {
			throw new RuntimeException("method 값이 없습니다.");
		}
	
		this.url = url;
		this.method = method;
		this.parameters = parameters;
		this.attributes = attributes;
	}
	
	public String getUrl() {
		return url;
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
}
