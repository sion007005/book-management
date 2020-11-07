package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategorySearchCondition;
import sion.bookmanagement.service.CategorySearchCondition.SearchType;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");

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
		mav.put("categoryList", categoryList);
		
		return mav;
	}

}
