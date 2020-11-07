package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberOrderType;
import sion.bookmanagement.service.MemberService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class MemberListController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String orderType = (String) httpRequest.getParameter("order-type");
		MemberOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = MemberOrderType.valueOf(orderType);
		}
		
		List<Member> memberList = memberService.findAll(type);
		ModelAndView mav = new ModelAndView("member_list");
		mav.put("memberList", memberList);
		 
		return mav;
	}
	
}
