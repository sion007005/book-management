package sion.bookmanagement.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;

@Controller
public class BookInfoController implements ControllerAware {
	private BookService bookService = BookService.getInstance();

	@Login
	@Override
	@GetMapping("/books/info")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String)request.getParameter("id"));
		Book book = bookService.findOneById(id);
		ModelAndView mav = new ModelAndView("book_info");
		mav.addObject("book", book);
		
		return mav;
		
	}
}
