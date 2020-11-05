package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryInfoController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String) httpRequest.getParameter("id"));
		Category category = categoryService.findOneById(id);
		
		Model model = new Model();
		model.put("category", category);
		
		return new HttpResponse(model, "category_info");
	}
	
}
