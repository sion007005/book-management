package sion.bookmanagement.controller;

import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberRemoveController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		memberService.remove(id);
		
		Model model = new Model();
		model.put("memberId", id);
		
		return new HttpResponse(model,  HttpResponse.REDIRECT_NAME + "/members/list"); //forwarding이 아닌 전체 리스트로 redirect로 한다.
	}
}
