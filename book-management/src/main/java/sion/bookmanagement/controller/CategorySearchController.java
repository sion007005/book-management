package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");

		List<Category> categoryList = null;
		
		if (searchType.equals("id")) {
			categoryList = categoryService.searchById(NumberUtils.parseInt(keyword));
		} else {
			categoryList = categoryService.search(keyword);
		}
		
		return new HttpResponse<List<Category>>(categoryList, "category_list");
	}

}
