package sion.bookmanagement.controller;

import sion.bookmanagement.auth.BookManagementUser;
import sion.mvc.Cookie;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;
import sion.mvc.support.CookieUtils;

public class LogoutProcessController implements Controller {
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		Cookie cookie = new Cookie();
		cookie.setDomain("localhost");
		cookie.setPath("/");
		cookie.setName("sid"); 
		cookie.setValue(""); //sid값 지워준다.
		
		UserContext.remove(); 
		UserContext.set(BookManagementUser.newLogoutUser(httpRequest.getAccessIp()));
		CookieUtils.setValue(httpResponse.getHeaders(), cookie);
		
		return new ModelAndView(HttpResponse.REDIRECT_NAME + "/login/form");
	}
}
