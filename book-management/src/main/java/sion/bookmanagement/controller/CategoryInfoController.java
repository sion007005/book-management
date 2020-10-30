package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryInfoController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String) httpRequest.getParameter("id"));
		Category category = categoryService.findOneById(id);
		
		return new HttpResponse<Category>(category, "category_info");
	}
	
}
