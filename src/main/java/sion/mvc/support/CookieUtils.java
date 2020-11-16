package sion.mvc.support;

import com.sun.net.httpserver.Headers;

import lombok.extern.slf4j.Slf4j;
import sion.http.Cookie;

@Slf4j
public class CookieUtils {
//Cookie: test1=1234; test2=abcd; sid=1; sid=0
	public static String getValue(Headers headers, String key) {
		String cookie = headers.getFirst("Cookie"); 
		log.info("Cookie: "+ cookie);
		
		//첫번째 sid 에 대한 값을 파싱해서 리턴하기 (위에 주석에서는 1) 
		if (cookie == null) {
			return null;
		}
		
		int keyIdx = cookie.indexOf(key);
		if (keyIdx < 0) {
			return null;
		}
		
		String temp = cookie.substring(keyIdx);
		String value = temp.substring(temp.indexOf("=")+1);
		
		if (value.contains(";")) {
			value = value.replace(";", "");
		}
		
		return value;
	}
	
	// 아이디 패스워드를 로그인 페이지에서 치고 로그인 눌렀을 때, 맞는 정보일 경우 쿠키를 굽는다(http header에 set cookie하는 역할)
	// 로그인 성공하면 sid=${memberId} , 로그아웃하면 sid=""으로 쿠키를 구워야 함  
	public static void setValue(Headers headers, Cookie cookie) {
		headers.add("Set-Cookie", cookie.generateCookieValue());
	}
	
	
	
}
