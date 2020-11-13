package sion.bookmanagement.controller;

import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberRemoveController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		memberService.remove(id);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
		mav.addObject("memberId", id);
		
		return mav; //forwarding이 아닌 전체 리스트로 redirect로 한다.
	}
}
