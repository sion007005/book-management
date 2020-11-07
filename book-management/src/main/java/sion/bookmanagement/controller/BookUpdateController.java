package sion.bookmanagement.controller;


import java.util.Date;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.service.CategoryService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookUpdateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();

	@Override
	@Login
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		int categoryIdNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("category_id"));
		String trimedTitle = StringUtils.trim((String)httpRequest.getAttribute("title"));
		String trimedAuthor = StringUtils.trim((String)httpRequest.getAttribute("author"));
		int stockNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("stock"));
		int yearNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("year"));
		int priceNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("price"));
		Date createdAt = DateUtils.getDate((String)httpRequest.getAttribute("createdAt"));
		int bookIdNumber = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		
		Book book = new Book(categoryIdNumber, trimedTitle, trimedAuthor, stockNumber, yearNumber, priceNumber);
		book.setId(bookIdNumber);
		book.setCreatedAt(createdAt);
		book.setUpdatedAt(new Date());
		
		bookValidator.validate(book);
		bookService.update(book);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/books/info?id=" + bookIdNumber);
		mav.put("book", book);
		
		return mav;
	}

}
