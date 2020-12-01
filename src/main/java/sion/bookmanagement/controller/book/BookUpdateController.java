package sion.bookmanagement.controller.book;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

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
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

@Slf4j
public class BookUpdateController implements Controller {
	private BookValidator bookValidator = new BookValidator();
	private BookService bookService = BookService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	public final static String UPLOAD_ROOT_DIRECTORY = "C:/uploaded";
	public final static String UPLOAD_BOOK_DIRECTORY = "/book";

	@Login
	@Override
	@PostMapping("/books/update")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		try {
			MultipartRequest mr = new MultipartRequest(request, "C:/uploaded" + UPLOAD_BOOK_DIRECTORY, 1024 * 1024 * 10, "UTF-8");

			int bookIdNumber = NumberUtils.removeComma((String) mr.getParameter("id"));
			int categoryIdNumber = NumberUtils.parseInt((String) mr.getParameter("form-category-select"));
			int priceNumber = NumberUtils.removeComma((String) mr.getParameter("price"));
			int yearNumber = NumberUtils.parseInt((String) mr.getParameter("year"));
			int stockNumber = NumberUtils.parseInt((String) mr.getParameter("stock"));
			Date createdAt = DateUtils.getDate((String) mr.getParameter("createdAt"));
			String trimedTitle = StringUtils.trim((String) mr.getParameter("title"));
			String trimedAuthor = StringUtils.trim((String) mr.getParameter("author"));
			String imgPath = (String) mr.getParameter("imgPath");
			String file = mr.getFilesystemName("file");
			
			Book book = new Book(categoryIdNumber, trimedTitle, trimedAuthor, stockNumber, yearNumber, priceNumber);
			book.setId(bookIdNumber);
			book.setCreatedAt(createdAt);
			book.setUpdatedAt(new Date());

			if (Objects.isNull(file)) {
				book.setImgPath(imgPath);
			} else {
				book.setImgPath(UPLOAD_BOOK_DIRECTORY + "/" + file);
			}

			bookValidator.validate(book);
			bookService.update(book);

			ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
			mav.addObject("proccessed", true);
			mav.addObject("bookId", bookIdNumber);

			return mav;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
