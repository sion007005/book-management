package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class MemberFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		ModelAndView mav = new ModelAndView("member_form");
		
		String id = (String) httpRequest.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Member member = memberService.findOneById(NumberUtils.parseInt(id));
			mav.put("member", member);
		}
		
		return mav;
	}
}
