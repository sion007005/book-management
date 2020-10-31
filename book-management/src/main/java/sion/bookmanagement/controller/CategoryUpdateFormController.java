package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class CategoryUpdateFormController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		Category category = categoryService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		
		Model model = new Model();
		model.put("category", category);
		return new HttpResponse(model, "category_update_form");
	}

}
