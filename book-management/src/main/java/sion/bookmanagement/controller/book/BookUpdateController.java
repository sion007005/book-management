package sion.bookmanagement.controller.book;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.http.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;

public class BookUpdateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();

	@Override
	@Login
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int categoryIdNumber = NumberUtils.parseInt((String)request.getAttribute("category_id"));
		String trimedTitle = StringUtils.trim((String)request.getAttribute("title"));
		String trimedAuthor = StringUtils.trim((String)request.getAttribute("author"));
		int stockNumber = NumberUtils.parseInt((String)request.getAttribute("stock"));
		int yearNumber = NumberUtils.parseInt((String)request.getAttribute("year"));
		int priceNumber = NumberUtils.parseInt((String)request.getAttribute("price"));
		Date createdAt = DateUtils.getDate((String)request.getAttribute("createdAt"));
		int bookIdNumber = NumberUtils.parseInt((String)request.getParameter("id"));
		
		Book book = new Book(categoryIdNumber, trimedTitle, trimedAuthor, stockNumber, yearNumber, priceNumber);
		book.setId(bookIdNumber);
		book.setCreatedAt(createdAt);
		book.setUpdatedAt(new Date());
		
		bookValidator.validate(book);
		bookService.update(book);
		
		ModelAndView mav = new ModelAndView(HttpResponse.REDIRECT_NAME + "/books/info?id=" + bookIdNumber);
		mav.addObject("book", book);
		
		return mav;
	}

}
