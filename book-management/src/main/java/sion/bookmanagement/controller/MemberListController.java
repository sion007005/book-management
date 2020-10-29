package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberOrderType;
import sion.bookmanagement.service.MemberService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberListController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String orderType = (String) httpRequest.getParameter("order-type");
		MemberOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = MemberOrderType.valueOf(orderType);
		}
		
		//TODO 메소드 네이밍  시 규칙을 세워서 통일 시키기
		List<Member> memberList = memberService.findAll(type);
		
		// TODO member_list /member_main_list 든지 컨트롤러명이랑 같이 전체적으로 이름을 통일시킬 것 
		return new HttpResponse<List<Member>>(memberList, "member_list");
	}
	
}
