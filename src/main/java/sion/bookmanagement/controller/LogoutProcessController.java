package sion.bookmanagement.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.auth.BookManagementUser;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.render.ViewRender;

public class LogoutProcessController implements Controller {
	@Override
	@GetMapping("/logout")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("sid", "");
		cookie.setPath("/");
		
		UserContext.remove(); 
		UserContext.set(BookManagementUser.newLogoutUser(request.getLocalAddr()));
		response.addCookie(cookie);
		
		String returnUrl = request.getRequestURL().toString();
		//TODO 로그아웃시 돌아갈 위치 지정(menu.ftl에서 로그아웃 버튼을 누를 때 현재 페이지 정보를 받아올 방법이 없음) 
		return new ModelAndView(ViewRender.REDIRECT_NAME + "/login/form");
	}
}
