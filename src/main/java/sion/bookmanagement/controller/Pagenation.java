package sion.bookmanagement.controller;

import lombok.Getter;

/** 
 * 용어 설명 붙여넣기(item은 뭘 의미하는지 등)
 * 전체 게시물수 555개
 * 한페이지당 게시글 수(PAGE_SIZE) : 10
 * 최종 마지막 페이지 : 56
 * 
 * 그룹 start page : 13
 * current page : 18
 * 그룹 end page : 22
 * 이전 링크 : 현재페이지 12 페이지
 * 다음 링크 : 현재페이지 23 페이
 * 
 * 이전 13 14 15 16 17 18 19 20 21 22 다음
 * 현재 페이지가 1 페이지면 "이전" 이 안보이고, 최종 마지막 페이지면 "다음"이 안보인다.
 * 
 * pagenation.ftl을 만들고 모든 목록페이지에 include 시키기
 *
 */
@Getter
public class Pagenation {
	public final static int ITEM_SIZE_PER_PAGE = 10;  // 한 페이지당 리스트 수 
	public final static int RANGE_SIZE_PER_BLOCK = 10; // 한 블럭 당 페이지 수(페이지 범위) 

	private int curPage; // 현재 페이지
	private int limit;
	private int offset;
	private int totalItemCnt; // 전체 리스트 수
	private int totalPageCnt; // 전체 페이지 수
	private int totalRangeCnt; // 전체 페이지 블럭 수 
	
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private boolean prev; // 이전 페이지 존재 여부
	private boolean next; // 다음 페이지 존재 여부
	
	/**
	 * 페이징 처리 순서
	 * 1. 총 페이지수
	 * 2. 총 블럭(range)수
	 * 3. range setting
	 */
	public Pagenation(int totalItemCnt, int curPage) {
		this.totalItemCnt = totalItemCnt;
		this.curPage = (curPage == 0) ? 1 : curPage;
		this.offset = Pagenation.ITEM_SIZE_PER_PAGE;
		
		this.limit = calculateLimit();
		this.totalPageCnt = calculateTotalPageCnt();
		this.totalRangeCnt = calculateTotalRangeCnt();
//      // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.
//		setTotalListCnt(totalListCnt);
//      setCurPage(curPage);
//      
//      /** 1. 총 페이지 수 **/
//      setTotalPageCnt(totalListCnt);
//      /** 2. 총 블럭(range)수 **/
//      setTotalRangeCnt(totalListCnt);
//      /** 3. 블럭(range) setting **/
//      rangeSetting(curPage);
//      
//      /** DB 질의를 위한 startIndex 설정 **/
//      setStartIndex(curPage);
//      
//      //TODO 메소드로 분리하기
//      this.prev = (curPage == 1) ? false : true;
//
//		//다음 버튼 상태
//		this.next = (endPage >= totalPageCnt) ? false : true;
//
//		if (this.endPage > this.totalPageCnt) {
//			this.endPage = this.totalPageCnt;
//			this.next = false;
//		}
	}

	public int calculateLimit() {
		return (this.curPage - 1) * this.offset; 
	}

	public int calculateTotalPageCnt() {
		if (this.totalItemCnt % this.offset == 0) {
			return this.totalItemCnt / this.offset;
		} 
		
		return this.totalItemCnt / this.offset + 1;
	}

	public int calculateTotalRangeCnt() {
		if (this.totalItemCnt % (this.offset * Pagenation.RANGE_SIZE_PER_BLOCK) == 0) {
			return this.totalItemCnt / (this.offset * Pagenation.RANGE_SIZE_PER_BLOCK);
		}
		
		return this.totalItemCnt / (this.offset * Pagenation.RANGE_SIZE_PER_BLOCK) + 1;
	}

	
//	public int getListSize() {
//		return ITEM_SIZE;
//	}
//
//	public int getRangeSize() {
//		return RANGE_SIZE;
//	}
//
//	public int getCurPage() {
//		return curPage;
//	}
//
//	public void setCurPage(int curPage) {
//		this.curPage = curPage;
//	}
//
//	public int getCurRange() {
//		return curRange;
//	}
//
//	public void setCurRange(int curRange) {
//		this.curRange = curRange;
//	}
//
//	public int getTotalListCnt() {
//		return totalItemCnt;
//	}
//
//	public void setTotalListCnt(int totalListCnt) {
//		this.totalItemCnt = totalListCnt;
//	}
//
//	public int getTotalPageCnt() {
//		return totalPageCnt;
//	}
//	// 로직에 계산이 들어가면 set을 쓰지않음
//	public void setTotalPageCnt(int totalListCnt) {
//		if (totalListCnt %  ITEM_SIZE == 0) {
//			this.totalPageCnt = totalListCnt / ITEM_SIZE;
//			return;
//		}
//		
//		this.totalPageCnt = (totalListCnt /  ITEM_SIZE) + 1;
//	}
//
//	public int getTotalRangeCnt() {
//		return totalRangeCnt;
//	}
//	//TODO 메소드명 변경
//	public void setTotalRangeCnt(int totalRangeCnt) {
//		this.totalRangeCnt = (int) Math.ceil(totalPageCnt * 1.0 / totalRangeCnt);
//	}
//
//	public int getStartPage() {
//		return startPage;
//	}
//
//	public void setStartPage(int startPage) {
//		this.startPage = startPage;
//	}
//
//	public int getEndPage() {
//		return endPage;
//	}
//
//	public void setEndPage(int endPage) {
//		this.endPage = endPage;
//	}
//
//	public int getStartIndex() {
//		return startIndex;
//	}
//
//	public boolean isPrev() {
//		return prev;
//	}
//
//	public void setPrev(boolean prev) {
//		this.prev = prev;
//	}
//
//	public boolean isNext() {
//		return next;
//	}
//
//	public void setNext(boolean next) {
//		this.next = next;
//	}
//	//TODO 메소드는 동사로 시작, 클래스는 명사
//	public void rangeSetting(int curPage){
//      setCurRange(curPage);        
//      this.startPage = (curRange - 1) * RANGE_SIZE + 1;
//      this.endPage = startPage + RANGE_SIZE - 1;
//      
//      if(endPage > totalPageCnt){
//          this.endPage = totalPageCnt;
//      }
////      this.nextPage = curPage + 1;
//  }
//	
//	public void setStartIndex(int curPage) {
//     Pagenation.startIndex = (curPage-1) * ITEM_SIZE;
//	}
//	
}
