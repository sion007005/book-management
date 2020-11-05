package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryFormController implements Controller {
	CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		Model model = new Model();
		
		String id = (String) httpRequest.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Category category = categoryService.findOneById(NumberUtils.parseInt(id));
			List<Category> categoryList = categoryService.findAll(null);
			model.put("category", category);
			model.put("categoryList", categoryList);
		}
		
		return new HttpResponse(model, "category_form");
	}
}
