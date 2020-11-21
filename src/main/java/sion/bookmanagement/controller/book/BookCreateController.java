
package sion.bookmanagement.controller.book;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

@Controller
public class BookCreateController implements ControllerAware {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	
	@Login
	@Override
	@PostMapping("/books/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {	
		String trimedTitle = StringUtils.trim((String)request.getParameter("title"));
		String trimedAuthor = StringUtils.trim((String)request.getParameter("author"));
		int categoryId = NumberUtils.parseInt((String)request.getParameter("form-category-select"));
		int price = NumberUtils.parseInt((String)request.getParameter("price"));
		int year = NumberUtils.parseInt((String)request.getParameter("year"));
		int stock = NumberUtils.parseInt((String)request.getParameter("stock"));
		
		Book book = new Book(categoryId, trimedTitle, trimedAuthor, stock, year, price);
		book.setCreatedAt(new Date());
		book.setUpdatedAt(new Date());
		
		bookValidator.validate(book);
		int bookId = bookService.create(book);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/books/info?id="+bookId);
		mav.addObject("bookId", bookId);
		
		return mav;
	}
}
