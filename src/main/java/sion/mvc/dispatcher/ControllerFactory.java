package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;

public interface ControllerFactory {
	Commander getInstance(String key);
	String getKey(HttpServletRequest request);
}
