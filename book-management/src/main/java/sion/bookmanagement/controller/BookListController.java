package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookListController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String orderType = (String) httpRequest.getParameter("order-select");
		List<Book> bookList = bookService.getBookList(orderType);
		
		return new HttpResponse<List<Book>>(bookList, "book_main_list");
	}	
}
