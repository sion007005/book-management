package sion.bookmanagement.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookOrderType;
import sion.bookmanagement.service.book.BookSearchCondition;
import sion.bookmanagement.service.book.BookSearchCondition.SearchType;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.bookmanagement.util.validator.NoStringValueValidator;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
@Slf4j
public class BookSearchController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	@GetMapping("/books/search")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("book_list");
		String searchType = (String) request.getParameter("search-type");
		String orderType = (String) request.getParameter("order-type");
		String keyword = (String) request.getParameter("keyword");
		String page_ = (String)request.getParameter("page");
		int curPage = NumberUtils.parseInt((String)request.getParameter("page"), 1);

		int totalListCnt = bookService.getListCount();
		Pagenation pagenation = new Pagenation(totalListCnt, curPage);
		
		BookOrderType type = null;
		if (!StringUtils.isEmpty(orderType)) {
			type = BookOrderType.valueOf(orderType);
		}

		if (NoStringValueValidator.validate(keyword) || NoStringValueValidator.validate(searchType)) {
			List<Book> bookList = bookService.findAll(type);
			mav.addObject("bookList", bookList);
			mav.addObject("pagenation", pagenation);
			return mav;
		}

		BookSearchCondition condition = new BookSearchCondition();
		condition.setSearchType(SearchType.valueOf(searchType));
		condition.setKeyword(keyword);
		
		List<Book> bookList = bookService.search(condition, type);
		
		mav.addObject("bookList", bookList);
		mav.addObject("searchCondition", condition);
		mav.addObject("orderType", type);
		mav.addObject("pagenation", pagenation);
		
		return mav;
	}
}
