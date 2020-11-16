package sion.bookmanagement.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookOrderType;
import sion.bookmanagement.service.book.BookService;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class BookListController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
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
