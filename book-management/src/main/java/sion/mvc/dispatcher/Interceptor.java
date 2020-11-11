package sion.mvc.dispatcher;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public interface Interceptor {
	// 전처리 로직
	boolean preHandle(HttpRequest httpRequest, HttpResponse httpResponse, Controller controller);
	// 후처리 로직
	void postHandle(HttpRequest httpRequest, HttpResponse httpResponse, Controller controller);
}
