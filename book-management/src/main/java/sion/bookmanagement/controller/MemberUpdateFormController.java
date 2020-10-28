package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberUpdateFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		Member member = memberService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		return new HttpResponse<Member>(member, "member_update_form");
	}
	
}
