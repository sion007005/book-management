package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryOrderType;
import sion.bookmanagement.service.category.CategorySearchCondition;
import sion.bookmanagement.service.category.CategorySearchCondition.SearchType;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.bookmanagement.util.validator.NoStringValueValidator;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.render.ViewRender;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@GetMapping("/categories/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("category_list");
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");
		String orderType = (String) request.getParameter("order-type");

		if (NoStringValueValidator.validate(keyword) || NoStringValueValidator.validate(searchType)) {
			return new ModelAndView(ViewRender.REDIRECT_NAME + "/books/list?order-type="+request.getParameter("order-type"));
		}
		
		CategoryOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			type = CategoryOrderType.valueOf(orderType);
		}
		
		CategorySearchCondition condition = new CategorySearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		
		List<Category> categoryList = categoryService.search(condition, type);
		int totalItemCnt = categoryList.size();
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		curPage = (totalItemCnt == 0) ? 0 : curPage;
		Pagenation pagenation = new Pagenation(totalItemCnt, curPage);
		
		int endIdx = 0;
		if (Pagenation.limit + Pagenation.offset > totalItemCnt) {
			endIdx = totalItemCnt;
		} else {
			endIdx = Pagenation.limit + Pagenation.offset;
		}
		
		mav.addObject("categoryList", categoryList.subList(Pagenation.limit, endIdx));
		mav.addObject("searchCondition", condition);
		mav.addObject("keyword", keyword);
		mav.addObject("orderType", type);
		mav.addObject("pagenation", pagenation);
		mav.addObject("path", "/categories/search");
		
		return mav;
	}

}
