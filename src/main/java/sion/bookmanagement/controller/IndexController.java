package sion.bookmanagement.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;

public class IndexController implements Controller {

	@GetMapping("/")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		//return new ModelAndView(ViewRender.REDIRECT_NAME + "/members/list");
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("today", new Date());
		
		return mav;
	}

}
