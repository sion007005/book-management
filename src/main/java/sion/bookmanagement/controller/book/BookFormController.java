package sion.bookmanagement.controller.book;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapper;
import sion.mvc.dispatcher.Login;

public class BookFormController implements Controller {	
	private CategoryService categoryService = CategoryService.getInstance();
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	@GetMapper("/books/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("book_form");
		List<Category> categoryList = categoryService.findAll(null);
		mav.addObject("categoryList", categoryList);
		
		String id = (String) request.getParameter("id");
		if(!StringUtils.isEmpty(id)) {
			Book book = bookService.findOneById(NumberUtils.parseInt(id));
			mav.addObject("book", book);
		}
		
		return mav;
	}

}
