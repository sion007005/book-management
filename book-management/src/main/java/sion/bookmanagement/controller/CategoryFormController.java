package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class CategoryFormController implements Controller {
	CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		List<Category> categoryList = categoryService.findAll(null);
		
		Model model = new Model();
		model.put("categoryList", categoryList);
		
		return new HttpResponse(model, "category_form");
	}
}
