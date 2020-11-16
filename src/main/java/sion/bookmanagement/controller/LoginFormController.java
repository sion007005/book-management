package sion.bookmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.util.StringUtils;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;

public class LoginFormController implements Controller {

	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		if (UserContext.isLogin()) {
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
		}
		
		ModelAndView mav = new ModelAndView("login_form");

		String email = (String) request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			mav.addObject("email", email);
			mav.addObject("message", "아이디와 패스워드가 맞지 않습니다.");
		}
		
		return mav;
	}
}
