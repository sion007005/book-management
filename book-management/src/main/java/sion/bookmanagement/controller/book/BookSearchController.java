package sion.bookmanagement.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookSearchCondition;
import sion.bookmanagement.service.book.BookSearchCondition.SearchType;
import sion.bookmanagement.service.book.BookService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class BookSearchController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String searchType = (String) request.getParameter("search-type");
		String keyword = (String) request.getParameter("keyword");
		
		if (searchType == null || keyword == null) {
			//TODO 추후에 body에 여러개의 데이터를 내려줄 수 있다면, list_none 페이지는 하나만 있어도 된다.
			ModelAndView mav = new ModelAndView("book_list_none");
			return mav;
		} else {
			BookSearchCondition condition = new BookSearchCondition();
			condition.setSearchType(SearchType.valueOf(searchType));
			condition.setKeyword(keyword);
			
			List<Book> bookList = bookService.search(condition);
			
			ModelAndView mav = new ModelAndView("book_list");
			mav.addObject("bookList", bookList);
			
			return mav;
		}
	}

}
