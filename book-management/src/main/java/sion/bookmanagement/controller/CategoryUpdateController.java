package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryUpdateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		int categoryId = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		System.out.println("카테고리아이디 "+categoryId);
		String categoryName = (String)httpRequest.getAttribute("name");
		
		Category category = new Category(categoryName);
		category.setId(categoryId);
		categoryService.update(category);
		
		return new HttpResponse<Category>(category, HttpResponse.REDIRECT_NAME + "/categories/info?id=" + categoryId);
	}

}
