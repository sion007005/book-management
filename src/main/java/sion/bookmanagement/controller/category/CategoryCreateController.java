package sion.bookmanagement.controller.category;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class CategoryCreateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String categoryName = (String)request.getParameter("name");
		
		Category category = new Category(categoryName);
		category.setCreatedAt(new Date());
		category.setUpdatedAt(new Date());

		int categoryId = categoryService.create(category);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/categories/info?id="+categoryId);
		mav.addObject("categoryId", categoryId);
		
		return mav;
	}

}
