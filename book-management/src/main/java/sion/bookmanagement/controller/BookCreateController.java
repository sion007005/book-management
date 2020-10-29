
package sion.bookmanagement.controller;

import java.util.Date;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class BookCreateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	
	@Override
	public HttpResponse<Integer> command(HttpRequest httpRequest) {	
		String trimedTitle = StringUtils.trim((String)httpRequest.getAttribute("title"));
		String trimedAuthor = StringUtils.trim((String)httpRequest.getAttribute("author"));
		int categoryId = NumberUtils.parseInt((String)httpRequest.getAttribute("form-category-select"));
		int price = NumberUtils.parseInt((String)httpRequest.getAttribute("price"));
		int year = NumberUtils.parseInt((String)httpRequest.getAttribute("year"));
		int stock = NumberUtils.parseInt((String)httpRequest.getAttribute("stock"));
		
		Book book = new Book(categoryId, trimedTitle, trimedAuthor, stock, year, price, new Date());
		bookValidator.validate(book);
		bookService.create(book);
		
		return new HttpResponse<>(book.getId(), HttpResponse.REDIRECT_NAME + "/books/list");
	}
}
