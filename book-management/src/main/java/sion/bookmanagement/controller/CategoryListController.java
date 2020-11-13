package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryOrderType;
import sion.bookmanagement.service.CategoryService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class CategoryListController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String orderType = (String) httpRequest.getParameter("order-type");
		CategoryOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = CategoryOrderType.valueOf(orderType);
		}
		
		List<Category> categoryList = categoryService.findAll(type);
		
		ModelAndView mav = new ModelAndView("category_list");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}
	
}
