package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookUpdateFormController implements Controller {
	private BookService bookService = BookService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		Book book = bookService.findOneById(NumberUtils.parseInt((String) httpRequest.getParameter("id")));
		List<Category> categoryList = categoryService.findAll(null);
		
		Model model = new Model();
		model.put("book", book);
		model.put("categoryList", categoryList);
		
		return new HttpResponse(model, "book_form");
	}

}
