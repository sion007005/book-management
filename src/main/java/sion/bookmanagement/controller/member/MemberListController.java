package sion.bookmanagement.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
@Slf4j
public class MemberListController implements Controller {
	private MemberService memberService = MemberService.getInstance();

//	@Login
	@Override
	@GetMapping("/members/list")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		int totalListCnt = memberService.getListCount();
		Pagenation pagenation = new Pagenation(totalListCnt, curPage);
		
		MemberOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			log.info("orderType: {}", orderType);
			type = MemberOrderType.valueOf(orderType);
		}
		
		List<Member> memberList = memberService.findAll(type);
		
		ModelAndView mav = new ModelAndView("member_list");
		mav.addObject("memberList", memberList);
		mav.addObject("pagenation", pagenation);
		mav.addObject("path", "/members/list");
		return mav;
	}
	
}
