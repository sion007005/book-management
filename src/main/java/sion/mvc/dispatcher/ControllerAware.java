package sion.mvc.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;

public interface ControllerAware {
	ModelAndView command(HttpServletRequest request, HttpServletResponse response);
}
