package sion.bookmanagement.controller.book;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;
@Slf4j
public class BookFormController implements Controller {	
	private CategoryService categoryService = CategoryService.getInstance();
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	@GetMapping("/books/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("book_form");
		Pagenation.startIndex = 0;
		
		List<Category> categoryList = categoryService.findAll();
		String returnUrl = request.getRequestURI();
		mav.addObject("categoryList", categoryList);

		String id = (String) request.getParameter("id");
		if(!StringUtils.isEmpty(id)) {
			int idNumber = NumberUtils.removeComma(id);
			Book book = bookService.findOneById(idNumber);
			mav.addObject("book", book);
		}
		
		mav.addObject("returnUrl", returnUrl);
		return mav;
	}

}
