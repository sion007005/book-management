package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberSearchCondition;
import sion.bookmanagement.service.MemberSearchCondition.SearchType;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class MemberSearchController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String ageFromStr = (String) httpRequest.getParameter("age-from");
		String ageToStr = (String) httpRequest.getParameter("age-to");
		int ageFrom = NumberUtils.parseInt(ageFromStr, 0);
		int ageTo = NumberUtils.parseInt(ageToStr, 200);
		String keyword = (String) httpRequest.getParameter("keyword");

		if (searchType == null || keyword == null) {
			return new HttpResponse(null, "member_list_none");
		} else {
			MemberSearchCondition condition = new MemberSearchCondition();
			condition.setSearchType(SearchType.valueOf(searchType));
			condition.setKeyword(keyword);
			condition.setAgeFrom(ageFrom);
			condition.setAgeTo(ageTo);
			
			List<Member> memberList =  memberService.search(condition);
			
			Model model = new Model();
			model.put("memberList", memberList);
			
			return new HttpResponse(model, "member_list");
		}
	}
}