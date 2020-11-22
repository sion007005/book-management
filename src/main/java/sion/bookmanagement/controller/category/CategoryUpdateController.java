package sion.bookmanagement.controller.category;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

public class CategoryUpdateController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	@PostMapping("/categories/update")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int categoryId = NumberUtils.parseInt((String)request.getParameter("id"));
		String categoryName = (String)request.getParameter("name");
		Date createdAt = DateUtils.getDate((String)request.getParameter("createdAt"));
		
		Category category = new Category(categoryName);
		category.setId(categoryId);
		category.setCreatedAt(createdAt);
		category.setUpdatedAt(new Date());
		
		categoryService.update(category);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/categories/info?id=" + categoryId);
		mav.addObject("category", category);
		
		return mav;
	}
}
