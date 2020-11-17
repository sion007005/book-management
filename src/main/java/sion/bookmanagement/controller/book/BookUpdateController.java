package sion.bookmanagement.controller.book;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.service.category.CategoryService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.render.ViewRender;
@Slf4j
public class BookUpdateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();

	@Override
	@Login
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int categoryIdNumber = NumberUtils.parseInt((String)request.getParameter("form-category-select"));
		String trimedTitle = StringUtils.trim((String)request.getParameter("title"));
		String trimedAuthor = StringUtils.trim((String)request.getParameter("author"));
		int stockNumber = NumberUtils.parseInt((String)request.getParameter("stock"));
		int yearNumber = NumberUtils.parseInt((String)request.getParameter("year"));
		int priceNumber = NumberUtils.parseInt((String)request.getParameter("price"));
		Date createdAt = DateUtils.getDate((String)request.getParameter("createdAt"));
		int bookIdNumber = NumberUtils.parseInt((String)request.getParameter("id"));

		Book book = new Book(categoryIdNumber, trimedTitle, trimedAuthor, stockNumber, yearNumber, priceNumber);
		book.setId(bookIdNumber);
		book.setCreatedAt(createdAt);
		book.setUpdatedAt(new Date());
		
		bookValidator.validate(book);
		bookService.update(book);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/books/info?id=" + bookIdNumber);
		mav.addObject("book", book);
		
		return mav;
	}

}
