package sion.bookmanagement.controller;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookInfoController implements Controller {
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		Book book = bookService.findOneById(id);
		Model model = new Model();
		model.put("book", book);
		
		return new HttpResponse(model, "book_info");
		
	}
}
