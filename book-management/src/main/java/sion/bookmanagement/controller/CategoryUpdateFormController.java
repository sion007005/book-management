package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryUpdateFormController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		Category category = categoryService.findById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		return new HttpResponse<Category>(category, "category_update_form");
	}

}
