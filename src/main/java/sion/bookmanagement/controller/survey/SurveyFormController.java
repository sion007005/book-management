package sion.bookmanagement.controller.survey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;


public class SurveyFormController implements Controller {

	@Override
	@GetMapping("/survey/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/survey/survey_form");
		return mav;
	}

}
