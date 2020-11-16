package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategorySearchCondition;
import sion.bookmanagement.service.category.CategorySearchCondition.SearchType;
import sion.bookmanagement.service.category.CategoryService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");

		List<Category> categoryList = null;
		if (searchType == null || keyword == null) {
			ModelAndView mav = new ModelAndView("category_list_none");
			return mav;
		}
		
		CategorySearchCondition condition = new CategorySearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		
		categoryList = categoryService.search(condition);
		
		ModelAndView mav = new ModelAndView("category_list");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}

}
