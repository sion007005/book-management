
package sion.bookmanagement.controller.book;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookCreateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	
	@Override
	@Login
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {	
		String trimedTitle = StringUtils.trim((String)request.getAttribute("title"));
		String trimedAuthor = StringUtils.trim((String)request.getAttribute("author"));
		int categoryId = NumberUtils.parseInt((String)request.getAttribute("form-category-select"));
		int price = NumberUtils.parseInt((String)request.getAttribute("price"));
		int year = NumberUtils.parseInt((String)request.getAttribute("year"));
		int stock = NumberUtils.parseInt((String)request.getAttribute("stock"));
		
		Book book = new Book(categoryId, trimedTitle, trimedAuthor, stock, year, price);
		book.setCreatedAt(new Date());
		book.setUpdatedAt(new Date());
		
		bookValidator.validate(book);
		int bookId = bookService.create(book);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/books/info?id="+bookId);
		mav.addObject("bookId", bookId);
		
		return mav;
	}
}
