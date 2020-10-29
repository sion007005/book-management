package sion.bookmanagement.controller;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookInfoController implements Controller {
	private BookService bookService = BookService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		Book book = bookService.findOneById(id);
		
		return new HttpResponse<Book>(book, "book_info");
		
	}
}
