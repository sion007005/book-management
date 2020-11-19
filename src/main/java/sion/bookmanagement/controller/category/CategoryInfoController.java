package sion.bookmanagement.controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Commander;
import sion.mvc.dispatcher.GetMapper;
import sion.mvc.dispatcher.Login;

public class CategoryInfoController implements Commander {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	@GetMapper("/categories/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String) request.getParameter("id"));
		Category category = categoryService.findOneById(id);
		
		ModelAndView mav = new ModelAndView("category_info");
		mav.addObject("category", category);
		
		return mav;
	}
	
}
