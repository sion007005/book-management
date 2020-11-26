package sion.bookmanagement.controller;

import lombok.Getter;

/** 
 * item : 레코드
 * block : 한 화면에 보여지는 페이지 숫자 범위(1페이지~10페이지 1블럭)
 * 현재 페이지가 1 페이지면 "처음"이 안보이고, 최종 마지막 페이지면 "마지막"이 안보인다.
 */
@Getter
public class Pagenation {
	public final static int ITEM_SIZE_PER_PAGE = 10;  // 한 페이지당 리스트 수 
	public final static int RANGE_SIZE_PER_BLOCK = 10; // 한 블럭 당 페이지 수(페이지 범위) 
	public static int startIndex; // 가져올 레코드 시작 번호
	public static int endIndex; // 가져올 레코드 끝 번호

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
		Pagenation.startIndex = calculateStartIndex();
		Pagenation.endIndex = calculateEndIndex();
		this.totalPageCnt = calculateTotalPageCnt();
		this.totalBlockCnt = calculateTotalBlockCnt();
		this.curBlock = calculateCurBlock();
		this.startPage = calculateStartPage();
		this.endPage = calculateEndPage();
		this.prev = checkIsPrev();
		this.next = checkIsNext();
	}

	public int calculateStartIndex() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		return (this.curPage - 1) * ITEM_SIZE_PER_PAGE; 
	}

	private int calculateEndIndex() {
		if (Pagenation.startIndex + ITEM_SIZE_PER_PAGE > this.totalItemCnt) {
			return this.totalItemCnt;
		}
		
		return Pagenation.startIndex + ITEM_SIZE_PER_PAGE;
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
		
		return (this.curBlock - 1) * RANGE_SIZE_PER_BLOCK + 1;
	}

	public int calculateEndPage() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		if (this.curBlock == this.totalBlockCnt) {
			return this.totalPageCnt;
		}
		
		return this.curBlock * RANGE_SIZE_PER_BLOCK;
	}

	public int calculateCurBlock() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		return (int)((curPage-1) / RANGE_SIZE_PER_BLOCK) + 1;
	}

	public int calculateTotalPageCnt() {
		if (this.totalItemCnt == 0) {
			return 0;
		}
		
		if (this.totalItemCnt % ITEM_SIZE_PER_PAGE == 0) {
			return this.totalItemCnt / ITEM_SIZE_PER_PAGE;
		} 
		
		return this.totalItemCnt / ITEM_SIZE_PER_PAGE + 1;
	}

	public int calculateTotalBlockCnt() {
		if (this.totalItemCnt % (ITEM_SIZE_PER_PAGE * RANGE_SIZE_PER_BLOCK) == 0) {
			return this.totalItemCnt / (ITEM_SIZE_PER_PAGE * RANGE_SIZE_PER_BLOCK);
		}
		
		return this.totalItemCnt / (ITEM_SIZE_PER_PAGE * RANGE_SIZE_PER_BLOCK) + 1;
	}
}
