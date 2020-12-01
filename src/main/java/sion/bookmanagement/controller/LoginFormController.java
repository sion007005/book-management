package sion.bookmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;

public class LoginFormController implements Controller {
	
	@Override
	@GetMapping("/login/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String returnUrl = request.getParameter("returnUrl");
		ModelAndView mav = new ModelAndView("login_form");
		mav.addObject("returnUrl", returnUrl);

		String email = (String) request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			mav.addObject("email", email);
			mav.addObject("message", "아이디와 패스워드가 맞지 않습니다.");
		}
		
		return mav;
	}
}
