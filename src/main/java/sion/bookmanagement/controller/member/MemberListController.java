package sion.bookmanagement.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;

@Controller
public class MemberListController implements ControllerAware {
	private MemberService memberService = MemberService.getInstance();

//	@Login
	@Override
	@GetMapping("/members/list")
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
