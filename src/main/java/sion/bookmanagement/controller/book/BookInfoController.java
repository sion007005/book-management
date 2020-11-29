package sion.bookmanagement.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

public class BookInfoController implements Controller {
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	@GetMapping("/books/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String)request.getParameter("id"));
		String returnUrl = request.getRequestURI();
		
		Book book = bookService.findOneById(id);
//		String imgPath = 
		ModelAndView mav = new ModelAndView("book_info");
		mav.addObject("book", book);
		mav.addObject("returnUrl", returnUrl);
//		mav.addObject(("imgPath", imgPath);
		
		return mav;
		
	}
}
