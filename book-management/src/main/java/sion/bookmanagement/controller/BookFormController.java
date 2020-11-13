package sion.bookmanagement.controller;



import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookFormController implements Controller {	
	private CategoryService categoryService = CategoryService.getInstance();
	private BookService bookService = BookService.getInstance();

	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		ModelAndView mav = new ModelAndView("book_form");
		List<Category> categoryList = categoryService.findAll(null);
		mav.addObject("categoryList", categoryList);
		
		String id = (String) httpRequest.getParameter("id");
		if(!StringUtils.isEmpty(id)) {
			Book book = bookService.findOneById(NumberUtils.parseInt(id));
			mav.addObject("book", book);
		}
		
		return mav;
	}

}
