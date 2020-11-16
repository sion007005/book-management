package sion.bookmanagement.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class MemberListController implements Controller {
	private MemberService memberService = MemberService.getInstance();

//	@GetMapper("/member/list")
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
		MemberOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = MemberOrderType.valueOf(orderType);
		}
		
		List<Member> memberList = memberService.findAll(type);
		ModelAndView mav = new ModelAndView("member_list");
		mav.addObject("memberList", memberList);
		 
		return mav;
	}
	
}
