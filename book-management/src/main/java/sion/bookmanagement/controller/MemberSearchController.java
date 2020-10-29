package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberSearchCondition;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class MemberSearchController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String ageFromStr = (String) httpRequest.getParameter("age-from");
		String ageToStr = (String) httpRequest.getParameter("age-to");
		int ageFrom = NumberUtils.parseInt(ageFromStr, 0);
		int ageTo = NumberUtils.parseInt(ageToStr, 200);
		String keyword = (String) httpRequest.getParameter("keyword");

		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setSearchType(searchType);
		condition.setKeyword(keyword);
		condition.setAgeFrom(ageFrom);
		condition.setAgeTo(ageTo);
		
		List<Member> memberList =  memberService.search(condition);

		return new HttpResponse<List<Member>>(memberList, "member_list");
	}

}