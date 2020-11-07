package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberSearchCondition;
import sion.bookmanagement.service.MemberSearchCondition.SearchType;
import sion.bookmanagement.service.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class MemberSearchController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Override
	@Login                                                                                                         
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String ageFromStr = (String) httpRequest.getParameter("age-from");
		String ageToStr = (String) httpRequest.getParameter("age-to");
		int ageFrom = NumberUtils.parseInt(ageFromStr, 0);
		int ageTo = NumberUtils.parseInt(ageToStr, 200);
		String keyword = (String) httpRequest.getParameter("keyword");

		if (searchType == null || keyword == null) {
			ModelAndView mav = new ModelAndView("member_list_none");
			return mav;
		} else {
			MemberSearchCondition condition = new MemberSearchCondition();
			condition.setSearchType(SearchType.valueOf(searchType));
			condition.setKeyword(keyword);
			condition.setAgeFrom(ageFrom);
			condition.setAgeTo(ageTo);
			
			List<Member> memberList =  memberService.search(condition);
			
			ModelAndView mav = new ModelAndView("member_list");
			mav.put("memberList", memberList);
			
			return mav;
		}
	}
}