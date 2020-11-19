package sion.bookmanagement.controller;

/**
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
public class Pagenation {

}
