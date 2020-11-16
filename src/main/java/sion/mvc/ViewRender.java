package sion.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewRender {
	void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav);
	
	public default void addHtmlContextHeader(HttpServletResponse response) {
		response.addHeader("Content-Type", "text/html; charset=UTF-8"); // "application/json;charset=UTF-8"
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
	
	public default void addStyleSheetContextHeader(HttpServletResponse response) {
		response.addHeader("Content-Type", "text/css; charset=UTF-8"); // "application/json;charset=UTF-8"
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
	
	public default void addJavaScriptContextHeader(HttpServletResponse response) {
		response.addHeader("Content-Type", "text/javascript; charset=UTF-8"); // "application/json;charset=UTF-8"
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
	
	public default void addJsonContextHeader(HttpServletResponse response) {
		response.addHeader("Content-Type", "application/json;charset=UTF-8"); 
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
}
