package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookSearchController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");
		
		List<Book> bookList = bookService.search(searchType, keyword);
		return new HttpResponse<List<Book>>(bookList, "book_list");
	}

}
