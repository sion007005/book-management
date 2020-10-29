package sion.bookmanagement.controller;

import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberRemoveController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public HttpResponse<Integer> command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		memberService.remove(id);
		
		return new HttpResponse<>(id,  HttpResponse.REDIRECT_NAME + "/members/list"); //forwarding이 아닌 전체 리스트로 redirect로 한다.
	}
}
