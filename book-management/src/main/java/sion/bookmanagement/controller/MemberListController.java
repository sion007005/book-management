package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberOrderType;
import sion.bookmanagement.service.MemberService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberListController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		String orderType = (String) httpRequest.getParameter("order-type");
		MemberOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = MemberOrderType.valueOf(orderType);
		}
		
		List<Member> memberList = memberService.findAll(type);
		Model model = new Model();
		model.put("memberList", memberList);
		 
		return new HttpResponse(model, "member_list");
	}
	
}
