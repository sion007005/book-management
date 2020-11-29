package sion.bookmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.controller.book.BookCreateController;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.render.ViewRender;
@Slf4j
public class ImageController implements Controller {
	@GetMapping("/image")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		String imgPath = (String) request.getParameter("imgPath");
		String imageFullPath = BookCreateController.UPLOAD_ROOT_DIRECTORY + imgPath;

		ModelAndView mav = new ModelAndView(ViewRender.IMG_VIEW_NAME);
		mav.addObject("imageFullPath", imageFullPath);
		
		return mav;
	}
}
