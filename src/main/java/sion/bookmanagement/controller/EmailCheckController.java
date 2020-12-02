package sion.bookmanagement.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;
@Slf4j
public class EmailCheckController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
	
	@Override
	@PostMapping("/check/email")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");

		Member member = memberService.findOneByEmail(email);
		if (Objects.nonNull(member)) {
			System.out.println("존재하는 멤버");
			mav.addObject("valid", false);
			mav.addObject("errorMessage", "이미 존재하는 이메일입니다.");
			return mav;
		}
		
		mav.addObject("valid", true);
		mav.addObject("message", "사용가능한 이메일입니다.");
		
		return mav;
	}

}
