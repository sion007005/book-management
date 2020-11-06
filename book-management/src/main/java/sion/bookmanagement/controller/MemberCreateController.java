package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.dispatcher.Controller;

public class MemberCreateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String trimedName = StringUtils.trim((String)httpRequest.getAttribute("name"));
		String trimedGender = StringUtils.trim((String)httpRequest.getAttribute("gender"));
		String emailFront = (String)httpRequest.getAttribute("email-front");
		String emailEnd = (String)httpRequest.getAttribute("form-email-select");
		String phone = (String)httpRequest.getAttribute("phone");
		int ageNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("age"));
		String password = (String)httpRequest.getAttribute("password");
		
		String emailAddress = emailFront + "@" + emailEnd;
		Member member = new Member(trimedName, trimedGender, emailAddress, ageNumber, phone, password);
		member.setCreatedAt(new Date());
		member.setUpdatedAt(new Date());
		
		memberValidator.validate(member);
		int memberId = memberService.create(member);
		
		return new HttpResponse(null, HttpResponse.REDIRECT_NAME + "/members/info?id=" + memberId);
	}
	
}
