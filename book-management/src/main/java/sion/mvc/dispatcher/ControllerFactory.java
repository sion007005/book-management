package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;

public interface ControllerFactory {
	Controller getInstance(String key);
	String getKey(HttpServletRequest request);
}
