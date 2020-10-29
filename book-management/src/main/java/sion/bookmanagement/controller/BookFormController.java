package sion.bookmanagement.controller;



import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookFormController implements Controller {	
	CategoryService categoryService = CategoryService.getInstance();

	@Override
	public HttpResponse<List<Category>> command(HttpRequest httpRequest) {
		List<Category> categoryList = categoryService.findAll(null);
		
		return new HttpResponse<>(categoryList, "book_form");
	}

}
