package sion.bookmanagement.controller.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryOrderType;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;

public class CategoryListController implements Controller {
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@GetMapping("/categories/list")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		int totalListCnt = categoryService.getListCount();
		Pagenation pagenation = new Pagenation(totalListCnt, curPage);
		
		CategoryOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			type = CategoryOrderType.valueOf(orderType);
		}
		
		List<Category> categoryList = categoryService.findAll(type);
		
		ModelAndView mav = new ModelAndView("category_list");
		mav.addObject("categoryList", categoryList);
		mav.addObject("pagenation", pagenation);
		mav.addObject("path", "/categories/list");
		
		return mav;
	}
	
}
