package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.service.MemberSortingType;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberListController implements Controller{
	private MemberService memberService = MemberService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String sorting = (String) httpRequest.getParameter("order-select");
		MemberSortingType sortingType = null;
		
		if (sorting != null && sorting.length() > 0) {
			sortingType = MemberSortingType.valueOf(sorting);
		}
		
		//TODO order-type 변수 이름 맞추기 (sorting) 
		//TODO 메소드 네이밍  시 규칙을 세워서 통일 시키기
		List<Member> memberList = memberService.findAll(sortingType);
		
		// TODO member_list /member_main_list 든지 컨트롤러명이랑 같이 전체적으로 이름을 통일시킬 것 
		return new HttpResponse<List<Member>>(memberList, "member_main_list");
	}
	
}
