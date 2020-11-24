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
		BookOrderType type = null;
		String orderType = (String) request.getParameter("order-type");
		String page_ = (String)request.getParameter("page");
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);
		
		if (!StringUtils.isEmpty(orderType)) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.findAll(type);
		int totalListCnt = bookList.size();
		
		ModelAndView mav = new ModelAndView("book_list");
		mav.addObject("bookList", bookList);
		mav.addObject("pagenation", new Pagenation(totalListCnt, curPage));
		return mav;
	}	
}
