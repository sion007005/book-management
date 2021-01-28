package sion.bookmanagement.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NumberUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_parseInt() {
		String text = "1234";
		int actual = NumberUtils.parseInt(text);
		int expected = 1234;
		assertEquals(expected, actual);
		
		String text_2 = "1.2";
		int defaultValue = 0;
		int actual_2 = NumberUtils.parseInt(text_2, defaultValue);
		int expected_2 = 0;
		assertEquals(expected_2, actual_2);
	}

	@Test
	public void test_removeComma() {
		String text = "12,34";
		int actual = NumberUtils.parseInt(text.replace(",", ""));
		int expected = 1234;
		assertEquals(expected, actual);
		
		String text_2 = "13,000";
		int actual_2 = NumberUtils.parseInt(text_2.replace(",", ""));
		int expected_2 = 13000;
		assertEquals(expected_2, actual_2);
	}
}
