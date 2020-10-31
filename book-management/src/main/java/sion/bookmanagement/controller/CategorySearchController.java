package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class CategorySearchController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");

		List<Category> categoryList = null;
		if (searchType == null || keyword == null) {
			return new HttpResponse(null, "category_list_none");
		}
		
		categoryList = categoryService.search(keyword);
		
		Model model = new Model();
		model.put("categoryList", categoryList);
		
		return new HttpResponse(model, "category_list");
	}

}
