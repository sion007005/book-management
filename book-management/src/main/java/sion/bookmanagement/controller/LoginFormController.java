package sion.bookmanagement.controller;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;

public class LoginFormController implements Controller {

	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		if (UserContext.isLogin()) {
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
		}
		
		ModelAndView mav = new ModelAndView("login_form");
		return mav;
	}
}
