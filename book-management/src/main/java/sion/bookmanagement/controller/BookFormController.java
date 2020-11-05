package sion.bookmanagement.controller;



import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookFormController implements Controller {	
	private CategoryService categoryService = CategoryService.getInstance();
	private BookService bookService = BookService.getInstance();

	@Override
	@Login
	public HttpResponse command(HttpRequest httpRequest) {
		Model model = new Model();
		List<Category> categoryList = categoryService.findAll(null);
		model.put("categoryList", categoryList);
		
		String id = (String) httpRequest.getParameter("id");
		if(!StringUtils.isEmpty(id)) {
			Book book = bookService.findOneById(NumberUtils.parseInt(id));
			model.put("book", book);
		}
		
		return new HttpResponse(model, "book_form");
	}

}
