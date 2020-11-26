package sion.bookmanagement.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberSearchCondition;
import sion.bookmanagement.service.member.MemberSearchCondition.SearchType;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.bookmanagement.util.validator.NoStringValueValidator;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;
import sion.mvc.render.ViewRender;

public class MemberSearchController implements Controller {
	private MemberService memberService = MemberService.getInstance();

	@Login                                                                                                         
	@Override
	@GetMapping("/members/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("member_list");
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");
		String orderType = (String) request.getParameter("order-type");
		String ageFromStr = (String) request.getParameter("age-from");
		String ageToStr = (String) request.getParameter("age-to");
		int ageFrom = NumberUtils.parseInt(ageFromStr, 0);
		int ageTo = NumberUtils.parseInt(ageToStr, 200);

		if (NoStringValueValidator.validate(keyword) || NoStringValueValidator.validate(searchType)) {
			return new ModelAndView(ViewRender.REDIRECT_NAME + "/members/list?order-type=" + request.getParameter("order-type"));
		} 
		
		MemberOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			type = MemberOrderType.valueOf(orderType);
		}

		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		condition.setAgeFrom(ageFrom);
		condition.setAgeTo(ageTo);
		
		List<Member> memberList =  memberService.search(condition, type);
		int totalItemCnt = memberList.size();
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		curPage = (totalItemCnt == 0) ? 0 : curPage;
		Pagenation pagenation = new Pagenation(totalItemCnt, curPage);
		
		int endIdx = 0;
		if (Pagenation.startIndex + Pagenation.endIndex > totalItemCnt) {
			endIdx = totalItemCnt;
		} else {
			endIdx = Pagenation.startIndex + Pagenation.endIndex;
		}
			
		mav.addObject("memberList", memberList.subList(Pagenation.startIndex, endIdx));
		mav.addObject("searchCondition", condition);
		mav.addObject("keyword", keyword);
		mav.addObject("orderType", type);
		mav.addObject("pagenation", pagenation);
		mav.addObject("path", "/members/search");
		
		return mav;
	}
}