package sion.bookmanagement.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookOrderType;
import sion.bookmanagement.service.book.BookService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Commander;
import sion.mvc.dispatcher.GetMapper;

public class BookListController implements Commander {
	private BookService bookService = BookService.getInstance();
	
	@Override
	@GetMapper("/books/list")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
		BookOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.findAll(type);
		ModelAndView mav = new ModelAndView("book_list");
		mav.addObject("bookList", bookList);
		mav.addObject("pagenation", new Pagenation());
		
		return mav;
	}	
}
