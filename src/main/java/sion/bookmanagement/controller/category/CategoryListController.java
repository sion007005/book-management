package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryOrderType;
import sion.bookmanagement.service.category.CategoryService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class CategoryListController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
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
