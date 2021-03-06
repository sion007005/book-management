package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;

public interface Interceptor {
	// 전처리 로직
	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Controller controller);
	// 후처리 로직
	void postHandle(HttpServletRequest request, HttpServletResponse response, Controller controller, ModelAndView mav);
}
