package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.mvc.Cookie;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.support.CookieUtils;

public class LoginProcessController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		//TODO validation check
		String email = (String)httpRequest.getAttribute("email");
		String inputPassword = (String)httpRequest.getAttribute("password");
		
		//이메일이 db에 있는지 search 
		//해당 이메일을 가진 멤버의  비밀번호도 같이 꺼내와서 비교 
		Member member = memberService.findOneByEmail(email);
		
		//일치하면 set-cookie 후 멤버 전체 리스트(임시)로 / 일치하지 않으면 로그인 페이지로 redirection
		if (inputPassword.equals(member.getPassword())) {
			Cookie cookie = new Cookie();
			cookie.setDomain("localhost");
			cookie.setPath("/");
			cookie.setName("sid");
			cookie.setValue(String.valueOf(member.getId()));
			
			CookieUtils.setValue(httpResponse.getHeaders(), cookie);
//			CookieUtils.setValue(httpExchange, "sid", member.getId());
			
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
		} else {
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/login/form");
		}
	}
}
