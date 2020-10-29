package sion.bookmanagement.controller;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookUpdateFormController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		Book book = bookService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		return new HttpResponse<Book>(book, "book_update_form");
	}

}
