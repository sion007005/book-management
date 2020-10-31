package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class BookSearchController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");
		
		if (searchType == null || keyword == null) {
			//TODO 추후에 body에 여러개의 데이터를 내려줄 수 있다면, list_none 페이지는 하나만 있어도 된다. 
			return new HttpResponse(null, "book_list_none");
		} else {
			List<Book> bookList = bookService.search(searchType, keyword);
			
			Model model = new Model();
			model.put("bookList", bookList);
			
			return new HttpResponse(model, "book_list");
		}
	}

}
