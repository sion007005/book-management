package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryListController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse<List<Category>> command(HttpRequest httpRequest) {
		String orderType = (String) httpRequest.getParameter("order-select");
		List<Category> categoryList = categoryService.findAll(orderType);
		
		return new HttpResponse<>(categoryList, "category_main_list");
	}
	
}
