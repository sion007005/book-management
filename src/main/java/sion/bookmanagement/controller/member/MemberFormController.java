package sion.bookmanagement.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

public class MemberFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Login
	@Override
	@GetMapping("/members/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("member_form");
		
		String id = (String) request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Member member = memberService.findOneById(NumberUtils.parseInt(id));
			mav.addObject("member", member);
		}
		
		return mav;
	}
}
