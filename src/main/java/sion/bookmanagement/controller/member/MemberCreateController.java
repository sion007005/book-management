package sion.bookmanagement.controller.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapper;
import sion.mvc.render.ViewRender;
import sion.mvc.support.SHA256Util;

public class MemberCreateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Login
	@Override
	@PostMapper("/members/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String trimedName = StringUtils.trim((String)request.getParameter("name"));
		String trimedGender = StringUtils.trim((String)request.getParameter("gender"));
		String emailFront = (String)request.getParameter("email-front");
		String emailEnd = (String)request.getParameter("form-email-select");
		String phone = (String)request.getParameter("phone");
		int ageNumber = NumberUtils.parseInt((String)request.getParameter("age"));
		String password = (String)request.getParameter("password");
		String salt  = SHA256Util.generateSalt();
      String newPassword = SHA256Util.getEncrypt(password, salt);
		String emailAddress = emailFront + "@" + emailEnd;
		
		Member member = new Member(trimedName, trimedGender, emailAddress, ageNumber, phone, newPassword);
		member.setCreatedAt(new Date());
		member.setUpdatedAt(new Date());
		member.setSalt(salt);
		
		memberValidator.validate(member);
		int memberId = memberService.create(member);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/members/info?id=" + memberId);
		return mav;
	}
	
}
