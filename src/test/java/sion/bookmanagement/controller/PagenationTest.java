package sion.bookmanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PagenationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_calculateCurPage() {
		int totalItemCnt = 100;
		int curPage = 0;
		Pagenation pagenation = new Pagenation(totalItemCnt, curPage);

		assertEquals(100, pagenation.getTotalItemCnt());
		assertEquals(1, pagenation.getCurPage());
		assertEquals(10, pagenation.getOffset());
	}
	
	@Test
	public void test_limit() {
		assertEquals(10, new Pagenation(100, 2).calculateLimit());
	}
	
	@Test
	public void test_totalPageCnt() {
		assertEquals(11, new Pagenation(105, 2).calculateTotalPageCnt());
		assertEquals(56, new Pagenation(554, 7).calculateTotalPageCnt());
	}

	@Test
	public void test_totalRangeCnt() {
		assertEquals(2, new Pagenation(150, 2).calculateTotalRangeCnt());
		assertEquals(1, new Pagenation(70, 2).calculateTotalRangeCnt());
	}
	
}
