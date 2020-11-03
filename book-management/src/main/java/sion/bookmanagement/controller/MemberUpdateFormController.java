package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberUpdateFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		Member member = memberService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		Model model = new Model();
		model.put("member", member);
		
		return new HttpResponse(model, "member_form");
	}
}
