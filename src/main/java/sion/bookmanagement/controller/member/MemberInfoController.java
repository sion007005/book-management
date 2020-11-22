package sion.bookmanagement.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

public class MemberInfoController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Login
	@Override
	@GetMapping("/members/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String) request.getParameter("id"));
		Member member = memberService.findOneById(id);
		ModelAndView mav = new ModelAndView("member_info");
		mav.addObject("member", member);
		
		return mav;
	}

}
