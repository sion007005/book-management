package sion.bookmanagement.controller.book;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookOrderType;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
@Slf4j
public class BookListController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	@GetMapping("/books/list")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String orderType = (String) request.getParameter("order-type");
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		int totalListCnt = bookService.getListCount();
		Pagenation pagenation = new Pagenation(totalListCnt, curPage);
		
		BookOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.findAll(type);
		
		ModelAndView mav = new ModelAndView("book_list");
		mav.addObject("bookList", bookList);
		mav.addObject("pagenation", pagenation);
		mav.addObject("path", "/books/list");
		return mav;
	}	
}
