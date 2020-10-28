package sion.bookmanagement.controller;


import java.util.Date;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookUpdateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		int categoryIdNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("category_id"));
		String trimedTitle = StringUtils.trim((String)httpRequest.getAttribute("title"));
		String trimedAuthor = StringUtils.trim((String)httpRequest.getAttribute("author"));
		int bookIdNumber = NumberUtils.parseInt((String)httpRequest.getParameter("id"));
		int stockNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("stock"));
		int yearNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("year"));
		int priceNumber = NumberUtils.parseInt((String)httpRequest.getAttribute("price"));
		
		Book book = new Book(categoryIdNumber, trimedTitle, trimedAuthor, stockNumber, yearNumber, priceNumber, new Date());
		book.setId(bookIdNumber);
		bookValidator.validate(book);
		bookService.updateBook(book);
		
		return new HttpResponse<Book>(book, HttpResponse.REDIRECT_NAME + "/books/info?id=" + bookIdNumber);
	}

}
