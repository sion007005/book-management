package sion.bookmanagement.controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

@Controller
public class CategoryInfoController implements ControllerAware {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	@GetMapping("/categories/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String) request.getParameter("id"));
		Category category = categoryService.findOneById(id);
		
		ModelAndView mav = new ModelAndView("category_info");
		mav.addObject("category", category);
		
		return mav;
	}
	
}
