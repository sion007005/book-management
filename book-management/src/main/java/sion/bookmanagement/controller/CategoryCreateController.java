package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class CategoryCreateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String categoryName = (String)httpRequest.getAttribute("name");
		
		Category category = new Category(categoryName);
		category.setCreatedAt(new Date());
		category.setUpdatedAt(new Date());

		int categoryId = categoryService.create(category);
		
		Model model = new Model();
		model.put("categoryId", categoryId);
		
		return new HttpResponse(model, HttpResponse.REDIRECT_NAME + "/categories/info?id="+categoryId);
	}

}
