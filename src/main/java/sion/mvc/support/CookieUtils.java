package sion.mvc.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sun.net.httpserver.Headers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtils {
//Cookie: test1=1234; test2=abcd; sid=1; sid=0
	public static String getValue(HttpServletRequest request, String name) {
		Cookie[] list = request.getCookies();
		
		
		if (list == null) 
			return null;
		
			for (Cookie cookie : list) {   
				if (cookie.getName().equals(name)) {    
					return cookie.getValue();   
				}  
			}
		
		return null;
	}
	
	// 아이디 패스워드를 로그인 페이지에서 치고 로그인 눌렀을 때, 맞는 정보일 경우 쿠키를 굽는다(http header에 set cookie하는 역할)
	// 로그인 성공하면 sid=${memberId} , 로그아웃하면 sid=""으로 쿠키를 구워야 함  
	public static void setValue(Headers headers, Cookie cookie) {
//		headers.add("Set-Cookie", cookie.generateCookieValue());
	}
	
	
	
}
