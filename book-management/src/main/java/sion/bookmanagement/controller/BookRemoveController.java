package sion.bookmanagement.controller;

import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.dispatcher.Controller;

public class BookRemoveController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse command(HttpRequest httpRequest) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		bookService.remove(id);
		
		Model model = new Model();
		model.put("bookId", id);
		
		return new HttpResponse(model, HttpResponse.REDIRECT_NAME + "/books/list");
	}

}
 