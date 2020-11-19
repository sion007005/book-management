package sion.bookmanagement.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Commander;
import sion.mvc.dispatcher.GetMapper;
import sion.mvc.dispatcher.Login;

@Slf4j
public class MemberInfoController implements Commander {
	private MemberService memberService = MemberService.getInstance();
	
	@Login
	@Override
	@GetMapper("/members/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String) request.getParameter("id"));
		Member member = memberService.findOneById(id);
		ModelAndView mav = new ModelAndView("member_info");
		mav.addObject("member", member);
		
		return mav;
	}

}
