package sion.bookmanagement.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.mvc.Cookie;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.support.AES256Util;
import sion.mvc.support.CookieUtils;
import sion.mvc.support.SHA256Util;
@Slf4j
public class LoginProcessController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		//TODO validation check
		String email = (String)httpRequest.getAttribute("email");
		String inputPassword = (String)httpRequest.getAttribute("password");
		
		//이메일이 db에 있는지 search 
		//해당 이메일을 가진 멤버의  암호화된 비밀번호와 salt를 같이 꺼내와서 비교한다. 
		Member member = memberService.findOneByEmail(email);
		
		String passwordForCheck = SHA256Util.getEncrypt(inputPassword, member.getSalt());
		
		
		//일치하면 set-cookie 후 멤버 전체 리스트(임시)로 / 일치하지 않으면 로그인 페이지로 redirection
		if (passwordForCheck.equals(member.getPassword())) {
			
			Cookie cookie = new Cookie();
			cookie.setDomain("localhost");
			cookie.setPath("/");
			cookie.setName("sid");
			
			try {
				AES256Util encryptUtil = new AES256Util();
				cookie.setValue(encryptUtil.encrypt(String.valueOf(member.getId())));
				log.debug("cookie sid : {}", cookie.getValue());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
			
			OffsetDateTime oneHourFromNow = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofHours(1));
			String cookieExpires = DateTimeFormatter.RFC_1123_DATE_TIME.format(oneHourFromNow);
			cookie.setExpires(cookieExpires);
			CookieUtils.setValue(httpResponse.getHeaders(), cookie);
			
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
		} else {
			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/login/form");
		}
	}
}