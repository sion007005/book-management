package sion.bookmanagement.controller;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.mvc.ModelAndView;
import sion.mvc.auth.LoginProcessException;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;
import sion.mvc.util.AES256Util;
import sion.mvc.util.SHA256Util;

@Slf4j
public class LoginProcessController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@PostMapping("/login")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		//TODO validation check
		String email = (String)request.getParameter("email");
		String plainPassword = (String)request.getParameter("password");
		String returnUrl = (String)request.getParameter("returnUrl");
		
		Member member = memberService.findOneByEmail(email);

		//이메일이 db에 있는지(db에 있는 회원인지) search 
		if (Objects.isNull(member)) {
			ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
			mav.addObject("login", false);
			mav.addObject("errorMessage", "회원 정보에 없는 이메일 주소이거나, 비밀번호가 올바르지 않습니다.");
			return mav;
		}
		
		//해당 이메일을 가진 멤버의  암호화된 비밀번호와 salt를 같이 꺼내와서 비교한다. 
		String encryptedPassword = SHA256Util.getEncrypt(plainPassword, member.getSalt());
		
		//일치하면 set-cookie 후 원래 가려던 페이지로 이동/ 일치하지 않으면 로그인 페이지로 redirection
		if (encryptedPassword.equals(member.getPassword())) {
			log.info("일치 쿠키세팅");
			try {
				AES256Util encryptUtil = new AES256Util();
//				OffsetDateTime oneHourFromNow = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofHours(1));
//				String cookieExpires = DateTimeFormatter.RFC_1123_DATE_TIME.format(oneHourFromNow);

				Cookie cookie = new Cookie("sid", encryptUtil.encrypt(String.valueOf(member.getId())));
//				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);

				ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
				mav.addObject("returnUrl", returnUrl);
				mav.addObject("login", true);
				return mav;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new LoginProcessException(e.getMessage(), e);
			} 
		} else {
			ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
			mav.addObject("returnUrl", returnUrl);
			mav.addObject("login", false);
			mav.addObject("email", email);
			mav.addObject("errorMessage", "아이디와 패스워드가 정확하지 않습니다.");
			return mav;
		}
	}
}