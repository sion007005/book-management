package sion.bookmanagement.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.auth.BookManagementUser;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.render.ViewRender;

@Controller
public class LogoutProcessController implements ControllerAware {
	@Override
	@GetMapping("/logout")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("sid", "");
		cookie.setDomain("localhost");
		cookie.setPath("/");
		
		UserContext.remove(); 
		UserContext.set(BookManagementUser.newLogoutUser(request.getLocalAddr()));
		response.addCookie(cookie);
		
		return new ModelAndView(ViewRender.REDIRECT_NAME + "/login/form");
	}
}
