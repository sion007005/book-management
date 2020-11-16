package sion.bookmanagement.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.auth.LoginProcessException;
import sion.mvc.dispatcher.Controller;
import sion.mvc.support.AES256Util;
import sion.mvc.support.SHA256Util;
@Slf4j
public class LoginProcessController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		//TODO validation check
		String email = (String)request.getAttribute("email");
		log.debug("넘어오는 이메일 값: {}", email);
		String plainPassword = (String)request.getAttribute("password");
		
		//이메일이 db에 있는지 search 
		//해당 이메일을 가진 멤버의  암호화된 비밀번호와 salt를 같이 꺼내와서 비교한다. 
		Member member = memberService.findOneByEmail(email);
		
		String encryptedPassword = SHA256Util.getEncrypt(plainPassword, member.getSalt());
		
		//일치하면 set-cookie 후 멤버 전체 리스트(임시)로 / 일치하지 않으면 로그인 페이지로 redirection
		if (encryptedPassword.equals(member.getPassword())) {
			try {
				AES256Util encryptUtil = new AES256Util();
//				OffsetDateTime oneHourFromNow = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofHours(1));
//				String cookieExpires = DateTimeFormatter.RFC_1123_DATE_TIME.format(oneHourFromNow);

				Cookie cookie = new Cookie("sid", encryptUtil.encrypt(String.valueOf(member.getId())));
				cookie.setDomain("localhost");
				cookie.setPath("/");
				
				response.addCookie(cookie);
				log.debug("cookie sid : {}", cookie.getValue());
				ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME +"/members/list");
				
				return mav;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new LoginProcessException(e.getMessage(), e);
			} 
		} else {
			ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/login/form?email="+email);
//			mav.put("message", "아이디와 패스워드가 맞지 않습니다.");
//			mav.put("email", email);
			
			return mav;
		}
	}
}