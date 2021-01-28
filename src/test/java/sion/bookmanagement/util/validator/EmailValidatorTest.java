package sion.bookmanagement.util.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailValidatorTest {

	@Test
	public void test_validate() {
		String content = "sion1313@naver.com";
//		String content_1 = "sion1313@yahoo.com";
//		String content_3 = "sionsionnaver.com";
//		String content_4 = "sion13'13@naver.com";
//		String content_5 = "sion1313@naver";
//		String content_6 = "sion1313 @naver.com";
//		String content_7 = "sion1313@com";
		
		assertTrue(new EmailValidator().validate(content));
//		assertTrue(new EmailValidator().validate(content_1));
//		assertFalse(new EmailValidator().validate(content_3));
//		assertFalse(new EmailValidator().validate(content_4));
//		assertFalse(new EmailValidator().validate(content_5));
//		assertFalse(new EmailValidator().validate(content_6));
//		assertFalse(new EmailValidator().validate(content_7));
	}
	
	@Test
	public void test_validate_blank() {
		String content_6 = "sion1313 @naver.com";
		
		assertFalse(new EmailValidator().validate(content_6));
	}

}
