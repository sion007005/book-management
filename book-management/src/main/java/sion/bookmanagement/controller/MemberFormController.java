package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class MemberFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		Model model = new Model();
		
		String id = (String) httpRequest.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Member member = memberService.findOneById(NumberUtils.parseInt(id));
			model.put("member", member);
		}
		
		return new HttpResponse(model, "member_form");
	}
}
