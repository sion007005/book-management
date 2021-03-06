package sion.bookmanagement.controller.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;
import sion.mvc.util.SHA256Util;
@Slf4j
public class MemberCreateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();

	@Login
	@Override
	@PostMapping("/members/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String trimedName = StringUtils.trim((String)request.getParameter("name"));
		String trimedGender = StringUtils.trim((String)request.getParameter("gender"));
		String emailFront = (String)request.getParameter("email-front");
		String emailEnd = (String)request.getParameter("form-email-select");
		String emailAddress = emailFront + "@" + emailEnd;
		String phone = (String)request.getParameter("phone");
		int ageNumber = NumberUtils.parseInt((String)request.getParameter("age"));
		String password = (String)request.getParameter("password");
		String salt  = SHA256Util.generateSalt();
      String newPassword = SHA256Util.getEncrypt(password, salt);
      
		// 생성자에 인자값이 많을 때는 아래 처럼 member.set 이렇게 값을 넣어 주는 것이 더 좋다
		Member member = new Member();
		member.setName(trimedName);
		member.setGender(trimedGender);
		member.setEmail(emailAddress);
		member.setAge(ageNumber);
		member.setPhone(phone);
		member.setPassword(newPassword);
		member.setCreatedAt(new Date());
		member.setUpdatedAt(new Date());
		member.setSalt(salt);
			
		memberValidator.validate(member);
		int memberId = memberService.create(member);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/members/info?id=" + memberId);
		return mav;
	}
	
}
