package sion.bookmanagement.controller;

import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryRemoveController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		categoryService.remove(id);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/categories/list");
		mav.put("categoryId", id);
		
		return mav;
	}

}
