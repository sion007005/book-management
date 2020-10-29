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
		
		List<Member> memberList = memberService.findAll(type);
		
		return new HttpResponse<List<Member>>(memberList, "member_list");
	}
	
}
