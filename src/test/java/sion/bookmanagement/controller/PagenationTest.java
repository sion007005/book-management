package sion.bookmanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		assertEquals(2, new Pagenation(150, 2).calculateTotalBlockCnt());
		assertEquals(1, new Pagenation(70, 2).calculateTotalBlockCnt());
	}
	
	@Test 
	public void test_calculateCurBlock() {
		assertEquals(3, new Pagenation(45, 23).calculateCurBlock());
		assertEquals(1, new Pagenation(3, 2).calculateCurBlock());
	}
	
	@Test 
	public void test_calculateStartPage() {
		assertEquals(11, new Pagenation(151, 14).calculateStartPage());
		assertEquals(11, new Pagenation(245, 17).calculateStartPage());
		assertEquals(21, new Pagenation(300, 25).calculateStartPage());
	}
	
	@Test
	public void test_calculateEndPage() {
		assertEquals(19, new Pagenation(190, 16).calculateEndPage());
		assertEquals(26, new Pagenation(256, 25).calculateEndPage());
	}
	
	@Test
	public void test_checkIsPrev() {
		assertTrue(new Pagenation(190, 2).checkIsPrev());
		assertFalse(new Pagenation(190, 1).checkIsPrev());
	}
	
	@Test
	public void test_checkIsNext() {
		assertTrue(new Pagenation(190, 2).checkIsPrev());
		assertFalse(new Pagenation(190, 19).checkIsNext());
	}
	
	
}
