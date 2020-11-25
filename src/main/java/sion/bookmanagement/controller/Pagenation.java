package sion.bookmanagement.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/** 
 * 용어 설명 붙여넣기(item은 뭘 의미하는지 등)
 * 현재 페이지가 1 페이지면 "이전" 이 안보이고, 최종 마지막 페이지면 "다음"이 안보인다.
 * pagenation.ftl을 만들고 모든 목록페이지에 include 시키기
 */
@Slf4j
@Getter
public class Pagenation {
	public final static int ITEM_SIZE_PER_PAGE = 10;  // 한 페이지당 리스트 수 
	public final static int RANGE_SIZE_PER_BLOCK = 10; // 한 블럭 당 페이지 수(페이지 범위) 
	public static int limit; // 가져올 레코드 시작 번호
	public static int offset; // 가져올 레코드 끝 번호

	private int curPage; // 현재 페이지
	private int totalItemCnt; // 전체 리스트 수
	private int totalPageCnt; // 전체 페이지 수
	private int totalBlockCnt; // 전체 페이지 블럭 수 
	private int curBlock; // 현재 페이지 블럭
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private boolean prev; // 이전 페이지 존재 여부
	private boolean next; // 다음 페이지 존재 여부
	
	public Pagenation(int totalItemCnt, int curPage) {
		this.curPage = (curPage == 0) ? 1 : curPage;
		this.totalItemCnt = totalItemCnt;
		Pagenation.limit = calculateLimit();
		Pagenation.offset = Pagenation.ITEM_SIZE_PER_PAGE;
		this.totalPageCnt = calculateTotalPageCnt();
		this.totalBlockCnt = calculateTotalBlockCnt();
		this.curBlock = calculateCurBlock();
		this.startPage = calculateStartPage();
		this.endPage = calculateEndPage();
		this.prev = checkIsPrev();
		this.next = checkIsNext();
	}

	public boolean checkIsPrev() {
		if (this.curPage == 1) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkIsNext() {
		if (this.curPage == this.totalPageCnt) {
			return false;
		}
		
		return true;
	}

	public int calculateStartPage() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		return (this.curBlock - 1) * Pagenation.RANGE_SIZE_PER_BLOCK + 1;
	}

	public int calculateEndPage() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		if (this.curBlock == this.totalBlockCnt) {
			return this.totalPageCnt;
		}
		
		return this.curBlock * Pagenation.RANGE_SIZE_PER_BLOCK;
	}


	public int calculateCurBlock() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		return (int)((curPage-1) / Pagenation.RANGE_SIZE_PER_BLOCK) + 1;
	}

	public int calculateLimit() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		return (this.curPage - 1) * Pagenation.offset; 
	}

	public int calculateTotalPageCnt() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		if (this.totalItemCnt % Pagenation.offset == 0) {
			return this.totalItemCnt / Pagenation.offset;
		} 
		
		return this.totalItemCnt / Pagenation.offset + 1;
	}

	public int calculateTotalBlockCnt() {
		if (this.totalItemCnt % (Pagenation.offset * Pagenation.RANGE_SIZE_PER_BLOCK) == 0) {
			return this.totalItemCnt / (Pagenation.offset * Pagenation.RANGE_SIZE_PER_BLOCK);
		}
		
		return this.totalItemCnt / (Pagenation.offset * Pagenation.RANGE_SIZE_PER_BLOCK) + 1;
	}
}
