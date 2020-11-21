package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;

public interface ControllerFactory {
	ControllerAware getInstance(String key);
	String getKey(HttpServletRequest request);
}
