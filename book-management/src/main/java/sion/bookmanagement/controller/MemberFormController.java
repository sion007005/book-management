package sion.bookmanagement.controller;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberFormController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		String id = (String) httpRequest.getParameter("id");
		Model model = new Model();
		
		if (StringUtils.isEmpty(id)) {
			//TODO 파일 하나로 합치기 (basic / update -> member_form으로)
			return new HttpResponse(model, "member_form");
		} else {
			Member member = memberService.findOneById(NumberUtils.parseInt(id));
			model.put("member", member);
			return new HttpResponse(model, "member_form");
		}
	}
}
