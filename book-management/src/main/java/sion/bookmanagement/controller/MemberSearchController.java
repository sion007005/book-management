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
		
//		if (ageFromStr == null || ageToStr == null) {
//			if (ageFromStr == null) ageFrom = 0;
//			if (ageToStr == null) ageTo = 200;
//		} else {
//			ageFrom = Integer.valueOf(ageFromStr);
//			ageTo = Integer.valueOf(ageToStr);
//		}
		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setSearchType(searchType);
		condition.setKeyword(keyword);
		condition.setAgeFrom(ageFrom);
		condition.setAgeTo(ageTo);
		
		List<Member> memberList =  memberService.search(condition);
		
//		if(searchType.equals("age")) {
//			System.out.println("나이로만 검색");
//			memberList = memberService.searchMemberByAge(ageFrom, ageTo);
//			
//			return new HttpResponse<List<Member>>(memberList, "member_main_list");
//		} else {
			
			//TODO ftl 파일 명 member_list로 변경
			return new HttpResponse<List<Member>>(memberList, "member_list");
//		}
		
	}

}