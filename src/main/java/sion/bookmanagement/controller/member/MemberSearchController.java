package sion.bookmanagement.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberSearchCondition;
import sion.bookmanagement.service.member.MemberSearchCondition.SearchType;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

@Controller
public class MemberSearchController implements ControllerAware {
	private MemberService memberService = MemberService.getInstance();

	@Login                                                                                                         
	@Override
	@GetMapping("/members/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String searchType = (String) request.getParameter("search-type");
		String ageFromStr = (String) request.getParameter("age-from");
		String ageToStr = (String) request.getParameter("age-to");
		int ageFrom = NumberUtils.parseInt(ageFromStr, 0);
		int ageTo = NumberUtils.parseInt(ageToStr, 200);
		String keyword = (String) request.getParameter("keyword");

		if (searchType == null || keyword == null) {
			ModelAndView mav = new ModelAndView("member_list_none");
			return mav;
		} 
		
		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		condition.setAgeFrom(ageFrom);
		condition.setAgeTo(ageTo);
		
		String orderType = (String) request.getParameter("order-type");
		MemberOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = MemberOrderType.valueOf(orderType);
		}
		
		List<Member> memberList =  memberService.search(condition, type);
		
		ModelAndView mav = new ModelAndView("member_list");
		mav.addObject("memberList", memberList);
		mav.addObject("searchCondition", condition);
		mav.addObject("orderType", type);
		
		return mav;
	}
}