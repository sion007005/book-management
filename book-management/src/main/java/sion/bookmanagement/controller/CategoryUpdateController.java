package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryUpdateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int categoryId = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		String categoryName = (String)httpRequest.getAttribute("name");
		Date createdAt = DateUtils.getDate((String)httpRequest.getAttribute("createdAt"));
		
		Category category = new Category(categoryName);
		category.setId(categoryId);
		category.setCreatedAt(createdAt);
		category.setUpdatedAt(new Date());
		
		categoryService.update(category);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/categories/info?id=" + categoryId);
		mav.put("category", category);
		
		return mav;
	}
}
