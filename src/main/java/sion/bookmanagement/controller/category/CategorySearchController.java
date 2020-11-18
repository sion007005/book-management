package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryOrderType;
import sion.bookmanagement.service.category.CategorySearchCondition;
import sion.bookmanagement.service.category.CategorySearchCondition.SearchType;
import sion.bookmanagement.service.category.CategoryService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapper;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@GetMapper("/categories/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");

		if (searchType == null || keyword == null) {
			ModelAndView mav = new ModelAndView("category_list_none");
			return mav;
		}
		
		CategorySearchCondition condition = new CategorySearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		
		String orderType = (String) request.getParameter("order-type");
		CategoryOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = CategoryOrderType.valueOf(orderType);
		}
		
		List<Category> categoryList = categoryService.search(condition, type);
		
		ModelAndView mav = new ModelAndView("category_list");
		mav.addObject("categoryList", categoryList);
		mav.addObject("searchCondition", condition);
		mav.addObject("orderType", type);
		
		return mav;
	}

}
