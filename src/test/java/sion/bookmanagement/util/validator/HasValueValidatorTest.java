package sion.bookmanagement.util.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HasValueValidatorTest {

	@Test
	public void test_validate() {
		String content = "즐거운 우리집";
		
		assertTrue(new EmailValidator().validate(content));
	}

}
