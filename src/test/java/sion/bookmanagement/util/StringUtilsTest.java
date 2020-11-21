package sion.bookmanagement.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void trim_null() {
		String text = null;
		String actual = StringUtils.trim(text);
		String expected = null;
		assertEquals(expected, actual);
	}
	
	@Test
	public void trim_text() {
		String text_1 = " abcd";
		String actual_1 = StringUtils.trim(text_1);
		String expected_1 = "abcd";
		assertEquals(expected_1, actual_1);
		
		String text_2 = "abcd ";
		String actual_2 = StringUtils.trim(text_2);
		String expected_2 = "abcd";
		assertEquals(expected_2, actual_2);
		
		String text_3 = " abcd ";
		String actual_3 = StringUtils.trim(text_3);
		String expected_3 = "abcd";
		assertEquals(expected_3, actual_3);
		
		String text_4 = " ab cd ";
		String actual_4 = StringUtils.trim(text_4);
		String expected_4 = "ab cd";
		assertEquals(expected_4, actual_4);
	}

}
