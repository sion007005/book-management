package sion.bookmanagement.controller;

import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookSearchCondition;
import sion.bookmanagement.service.BookSearchCondition.SearchType;
import sion.bookmanagement.service.BookService;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;

public class BookSearchController implements Controller {
	private BookService bookService = BookService.getInstance();
	
	@Override
	public ModelAndView command(HttpRequest httpRequest, HttpResponse httpResponse) {
		String searchType = (String) httpRequest.getParameter("search-type");
		String keyword = (String) httpRequest.getParameter("keyword");
		
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
			mav.put("bookList", bookList);
			
			return mav;
		}
	}

}
