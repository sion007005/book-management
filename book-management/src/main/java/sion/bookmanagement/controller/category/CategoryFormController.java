package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryFormController implements Controller {
	CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("category_form");
		
		String id = (String) request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Category category = categoryService.findOneById(NumberUtils.parseInt(id));
			List<Category> categoryList = categoryService.findAll(null);
			mav.addObject("category", category);
			mav.addObject("categoryList", categoryList);
		}
		
		return mav;
	}
}
