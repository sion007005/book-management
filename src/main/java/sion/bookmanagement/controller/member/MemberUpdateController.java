package sion.bookmanagement.controller.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberUpdateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();

	@Override
	@Login
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String trimedName = StringUtils.trim((String)request.getParameter("name"));
		String gender = (String)request.getParameter("gender");
		String emailFront = (String)request.getParameter("email-front");
		String emailEnd = (String)request.getParameter("form-email-select");
		String phone = (String)request.getParameter("phone");
		String password = (String)request.getParameter("password");
		int age = NumberUtils.parseInt((String)request.getParameter("age"));
		int memberId = NumberUtils.parseInt((String)request.getParameter("id"));
		Date createdAt = DateUtils.getDate((String)request.getParameter("createdAt"));
		
		String emailAddress = emailFront + "@" + emailEnd;
		Member member = new Member(trimedName, gender, emailAddress, age, phone, password);
		member.setId(memberId);
		member.setCreatedAt(createdAt);
		member.setUpdatedAt(new Date());
		
		memberValidator.validate(member);
		memberService.update(member);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/info?id=" + memberId);
		mav.addObject("member", member);
		
		return mav;
	}

}
