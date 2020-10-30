package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberUpdateController implements Controller {
	private MemberValidator memberValidator = new MemberValidator();
	private MemberService memberService = MemberService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String trimedName = StringUtils.trim((String)httpRequest.getAttribute("name"));
		String gender = (String)httpRequest.getAttribute("gender");
		String emailFront = (String)httpRequest.getAttribute("email-front");
		String emailEnd = (String)httpRequest.getAttribute("form-email-select");
		String phone = (String)httpRequest.getAttribute("phone");
		int age = NumberUtils.parseInt((String)httpRequest.getAttribute("age"));
		int memberId = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		Date createdAt = DateUtils.getDate((String)httpRequest.getAttribute("createdAt"));
		
		String emailAddress = emailFront + "@" + emailEnd;
		Member member = new Member(trimedName, gender, emailAddress, age, phone);
		member.setId(memberId);
		member.setCreatedAt(createdAt);
		member.setUpdatedAt(new Date());
		
		memberValidator.validate(member);
		memberService.update(member);
		
		return new HttpResponse<Member>(member, HttpResponse.REDIRECT_NAME + "/members/info?id=" + memberId); //forwarding이 아닌 redirect로 보내겠다!
	}

}
