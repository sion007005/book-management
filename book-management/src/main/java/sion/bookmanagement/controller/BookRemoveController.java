package sion.bookmanagement.controller;

import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookRemoveController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int id = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		bookService.remove(id);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/books/list");
		mav.addObject("bookId", id);
		
		return mav;
	}

}
 