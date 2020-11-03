package sion.bookmanagement.controller;

import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryRemoveController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		categoryService.remove(id);
		
		Model model = new Model();
		model.put("categoryId", id);
		
		return new HttpResponse(model, HttpResponse.REDIRECT_NAME + "/categories/list");
	}

}
