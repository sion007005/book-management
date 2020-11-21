package sion.bookmanagement.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookOrderType;
import sion.bookmanagement.service.book.BookSearchCondition;
import sion.bookmanagement.service.book.BookSearchCondition.SearchType;
import sion.bookmanagement.service.book.BookService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;

@Controller
public class BookSearchController implements ControllerAware {
	private BookService bookService = BookService.getInstance();
	
	@Override
	@GetMapping("/books/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");
		
		if (searchType == null || keyword == null) {
			//TODO 추후에 body에 여러개의 데이터를 내려줄 수 있다면, list_none 페이지는 하나만 있어도 된다.
			ModelAndView mav = new ModelAndView("book_list_none");
			return mav;
		}
		
		BookSearchCondition condition = new BookSearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		
		String orderType = (String) request.getParameter("order-type");
		BookOrderType type = null;
		
		if (orderType != null && orderType.length() > 0) {
			type = BookOrderType.valueOf(orderType);
		}
		
		List<Book> bookList = bookService.search(condition, type);
		
		ModelAndView mav = new ModelAndView("book_list");
		mav.addObject("bookList", bookList);
		mav.addObject("searchCondition", condition);
		mav.addObject("orderType", type);
		
		return mav;
	}
}
