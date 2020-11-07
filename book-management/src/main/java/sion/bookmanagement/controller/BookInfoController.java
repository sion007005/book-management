package sion.bookmanagement.controller;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookInfoController implements Controller {
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		Book book = bookService.findOneById(id);
		ModelAndView mav = new ModelAndView("book_info");
		mav.put("book", book);
		
		return mav;
		
	}
}
