package sion.bookmanagement.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.auth.BookManagementUser;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;

public class LogoutProcessController implements Controller {
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("sid", "");
		cookie.setDomain("localhost");
		cookie.setPath("/");
		
		UserContext.remove(); 
		UserContext.set(BookManagementUser.newLogoutUser(request.getLocalAddr()));
		response.addCookie(cookie);
//		CookieUtils.setValue(response.getHeaders(), cookie);
		
		return new ModelAndView(HttpResponse.REDIRECT_NAME + "/login/form");
	}
}
