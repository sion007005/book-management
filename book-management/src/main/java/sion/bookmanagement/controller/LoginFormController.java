package sion.bookmanagement.controller;

import sion.bookmanagement.util.StringUtils;
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

		String email = (String) httpRequest.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			mav.put("email", email);
			mav.put("message", "아이디와 패스워드가 맞지 않습니다.");
		}
		
		return mav;
	}
}
