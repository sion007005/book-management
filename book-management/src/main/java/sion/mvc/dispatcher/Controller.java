package sion.mvc.dispatcher;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;

public interface Controller {
	ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse);
}
