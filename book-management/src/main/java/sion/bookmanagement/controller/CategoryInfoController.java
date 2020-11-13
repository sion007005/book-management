package sion.bookmanagement.controller;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryInfoController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int id = NumberUtils.parseInt((String) httpRequest.getParameter("id"));
		Category category = categoryService.findOneById(id);
		
		ModelAndView mav = new ModelAndView("category_info");
		mav.addObject("category", category);
		
		return mav;
	}
	
}
