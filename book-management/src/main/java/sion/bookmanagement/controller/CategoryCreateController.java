package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryCreateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String categoryName = (String)httpRequest.getAttribute("name");
		
		Category category = new Category(categoryName);
		category.setCreatedAt(new Date());
		category.setUpdatedAt(new Date());

		int categoryId = categoryService.create(category);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/categories/info?id="+categoryId);
		mav.put("categoryId", categoryId);
		
		return mav;
	}

}
