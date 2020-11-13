package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookOrderType;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class BookListController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String orderType = (String) httpRequest.getParameter("order-type");
		BookOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.findAll(type);
		
//		ModelAndView mav = new ModelAndView("book_list");
		ModelAndView mav = new ModelAndView(HttpResponse.JSON_VIEW_NAME);
		mav.addObject("bookList", bookList);
		
		return mav;
	}	
}
