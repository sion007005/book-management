package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryCreateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse<Integer> command(HttpRequest httpRequest) {
		String categoryName = (String)httpRequest.getAttribute("name");
		Category category = new Category(categoryName);

		int categoryId = categoryService.registerCategory(category);
		
		return new HttpResponse<>(null, HttpResponse.REDIRECT_NAME + "/categories/info?id="+categoryId);
	}

}
