package sion.bookmanagement.controller;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class LoginFormController implements Controller {

	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		ModelAndView mav = new ModelAndView("login_form");
		return mav;
	}
}
