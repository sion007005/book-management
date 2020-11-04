package sion.bookmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

@Slf4j
public class MemberInfoController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String) httpRequest.getParameter("id"));
		
		log.debug("id :"+ id);
		
		Member member = memberService.findOneById(id);
		
		Model model = new Model();
		model.put("member", member);
		
		return new HttpResponse(model, "member_info");
	}

}
