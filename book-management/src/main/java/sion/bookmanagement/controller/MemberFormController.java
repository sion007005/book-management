package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class MemberFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String id = (String) httpRequest.getParameter("id");
		Model model = new Model();
		
		if (id == null || id.length() == 0) {
			//TODO 파일 하나로 합치기 (basic / update -> member_form으로)
			return new HttpResponse(model, "member_form");
		} else {
			Member member = memberService.findOneById(NumberUtils.parseInt(id));
			model.put("member", member);
			return new HttpResponse(model, "member_form");
		}
	}
}
