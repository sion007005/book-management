package sion.bookmanagement.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.service.book.BookService;
import sion.bookmanagement.util.NumberUtils;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.Login;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

@Controller
public class BookRemoveController implements ControllerAware {
	private BookService bookService = BookService.getInstance();
	
	@Login
	@Override
	@PostMapping("/books/remove")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int id = NumberUtils.parseInt((String)request.getParameter("id"));
		
		Validator<Integer> plusNumberValidater = new PlusNumberValidator();
		plusNumberValidater.validate(id);
		bookService.remove(id);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/books/list");
		mav.addObject("bookId", id);
		
		return mav;
	}

}
 