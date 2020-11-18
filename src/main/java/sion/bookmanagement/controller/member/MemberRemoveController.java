package sion.bookmanagement.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapper;
import sion.mvc.render.ViewRender;

public class MemberRemoveController implements Controller {
	private MemberService memberService = MemberService.getInstance();
	
	@Login
	@Override
	@PostMapper("/members/remove")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String)request.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		memberService.remove(id);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/members/list");
		mav.addObject("memberId", id);
		
		return mav; //forwarding이 아닌 전체 리스트로 redirect로 한다.
	}
}
