package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookOrderType;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookListController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String orderType = (String) httpRequest.getParameter("order-type");
		BookOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.findAll(type);
		
		return new HttpResponse<List<Book>>(bookList, "book_list");
	}	
}
