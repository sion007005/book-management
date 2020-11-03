package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryUpdateFormController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		Category category = categoryService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		List<Category> categoryList = categoryService.findAll(null);
		
		Model model = new Model();
		model.put("category", category);
		model.put("categoryList", categoryList);
		
		return new HttpResponse(model, "category_form");
	}

}
