package sion.bookmanagement.controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapper;
import sion.mvc.render.ViewRender;

public class CategoryRemoveController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Login
	@Override
	@PostMapper("/categories/remove")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String)request.getParameter("id"));
		categoryService.remove(id);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/categories/list");
		mav.addObject("categoryId", id);
		
		return mav;
	}

}
