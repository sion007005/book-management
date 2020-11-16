package sion.mvc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import sion.http.Cookie;

@Slf4j
public class CookieTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Cookie cookie = new Cookie();
		cookie.setDomain("localhost");
		cookie.setPath("/");
		cookie.setName("sid"); 
		cookie.setValue("30"); //sid값 지워준다.
		
		String actual = cookie.generateCookieValue();
		String expected = "sid=30; Path=/; Domain=localhost; Expires=";
		log.debug("actual : {}", actual);
		log.debug("expected : {}", expected);
		assertEquals(expected, actual);
		
	}

}
