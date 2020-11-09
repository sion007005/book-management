package sion.mvc;

import com.sun.net.httpserver.Headers;

public interface ViewRender {
	void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav);
	
	public default void addHtmlContextHeader(Headers headers) {
		headers.add("Content-Type", "text/html;charset=UTF-8"); // "application/json;charset=UTF-8"
		headers.add("Access-Control-Allow-Origin", "*");
	}
}
