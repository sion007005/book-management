package sion.bookmanagement.controller;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.dispatcher.Controller;

public class LoginFormController implements Controller {

	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		return new HttpResponse(null, "login_form");
	}

}
