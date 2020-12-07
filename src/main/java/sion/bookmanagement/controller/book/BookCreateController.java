
package sion.bookmanagement.controller.book;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.book.Book;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.ApplicationContext;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

@Slf4j
public class BookCreateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	public final static String UPLOAD_ROOT_DIRECTORY = "C:/uploaded"; 
	public final static String UPLOAD_BOOK_DIRECTORY = "/book";
	
	@Login
	@Override
	@PostMapping("/books/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {	
		
		try {
			// 서버의 루트 폴더 내에 book 폴더 안 
			MultipartRequest mr = new MultipartRequest(request, UPLOAD_ROOT_DIRECTORY + UPLOAD_BOOK_DIRECTORY, 1024 * 1024 * 10, ApplicationContext.getCharsetType());

			String trimedTitle = StringUtils.trim((String)mr.getParameter("title"));
			String trimedAuthor = StringUtils.trim((String)mr.getParameter("author"));
			int categoryId = NumberUtils.parseInt((String)mr.getParameter("form-category-select"));
			int price = NumberUtils.parseInt((String)mr.getParameter("price"));
			int year = NumberUtils.parseInt((String)mr.getParameter("year"));
			int stock = NumberUtils.parseInt((String)mr.getParameter("stock"));
			
			Book book = new Book(categoryId, trimedTitle, trimedAuthor, stock, year, price);
			book.setImgPath(UPLOAD_BOOK_DIRECTORY + "/" + mr.getFilesystemName("file"));
			book.setCreatedAt(new Date());
			book.setUpdatedAt(new Date());
			
			bookValidator.validate(book);
			int bookId = bookService.create(book);
			
			ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
			mav.addObject("proccessed", true);
			mav.addObject("bookId", bookId);
			
			return mav;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
