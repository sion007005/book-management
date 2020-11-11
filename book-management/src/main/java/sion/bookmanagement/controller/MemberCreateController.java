package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.support.SHA256Util;

public class MemberCreateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String trimedName = StringUtils.trim((String)httpRequest.getAttribute("name"));
		String trimedGender = StringUtils.trim((String)httpRequest.getAttribute("gender"));
		String emailFront = (String)httpRequest.getAttribute("email-front");
		String emailEnd = (String)httpRequest.getAttribute("form-email-select");
		String phone = (String)httpRequest.getAttribute("phone");
		int ageNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("age"));
		String password = (String)httpRequest.getAttribute("password");
		String salt  = SHA256Util.generateSalt();
      String newPassword = SHA256Util.getEncrypt(password, salt);
		String emailAddress = emailFront + "@" + emailEnd;
		
		Member member = new Member(trimedName, trimedGender, emailAddress, ageNumber, phone, newPassword);
		member.setCreatedAt(new Date());
		member.setUpdatedAt(new Date());
		member.setSalt(salt);
		
		memberValidator.validate(member);
		int memberId = memberService.create(member);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/info?id=" + memberId);
		return mav;
	}
	
}
