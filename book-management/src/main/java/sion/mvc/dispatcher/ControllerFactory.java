package sion.mvc.dispatcher;

import sion.mvc.HttpRequest;

public interface ControllerFactory {
	Controller getInstance(String key);
	String getKey(HttpRequest httpRequest);
}
