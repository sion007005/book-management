package sion.mvc.support;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.ForbiddenException;

@Slf4j
public class CookieUtils {
//Cookie: test1=1234; test2=abcd; sid=1; sid=0
	public static String getValue(HttpExchange httpExchange, String key) {
		Headers headers = httpExchange.getRequestHeaders();
		String cookie = headers.getFirst("Cookie"); 
		log.info("Cookie: "+ cookie);
		
		//TODO 첫번째 sid 에 대한 값을 파싱해서 리턴하기 (위에 주석에서는 1) 
		if (cookie == null) {
			return null;
		}
		
		int sidIdx = cookie.indexOf("sid");
		if (sidIdx < 0) {
			return null;
		}
		
		String temp = cookie.substring(sidIdx);
		String sid = temp.substring(temp.indexOf("=")+1);
		
		if(sid.contains(";")) {
			sid = sid.replace(";", "");
		}
		
		return sid;
	}
	
	// 아이디 패스워드를 로그인 페이지에서 치고 로그인 눌렀을 때, 맞는 정보일 경우 쿠키를 굽는다(http header에 set cookie하는 역할)
	// 로그인 성공하면 sid=${memberId} , 로그아웃하면 sid=""으로 쿠키를 구워야 함  
	public static void setValue(HttpExchange httpExchange, String key, String value) {
		Headers headers = httpExchange.getResponseHeaders();
		headers.add("Set-Cookie", key + "=" + value);
	}
	
	
	
}
