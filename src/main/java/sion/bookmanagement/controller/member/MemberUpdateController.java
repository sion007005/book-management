package sion.bookmanagement.controller.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;
@Slf4j
public class MemberUpdateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();

	@Login
	@Override
	@PostMapping("/members/update")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String trimedName = StringUtils.trim((String)request.getParameter("name"));
		String gender = (String)request.getParameter("gender");
		String emailFront = (String)request.getParameter("email-front");
		String emailEnd = (String)request.getParameter("email-end");
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
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/members/info?id=" + memberId);
		mav.addObject("member", member);
		
		return mav;
	}

}
