package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;

public interface ControllerFactory {
	Controller getController(String key);
	String getKey(HttpServletRequest request);
}
