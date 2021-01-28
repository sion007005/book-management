package sion.bookmanagement.controller.survey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.survey.Survey;
import sion.bookmanagement.service.survey.SurveyService;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;

public class SurveyListController implements Controller {
	private SurveyService surveyService = SurveyService.getInstance();
	
	@Override
	@GetMapping("/survey/list")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		List<Survey> surveyList = surveyService.findAll();
		
		ModelAndView mav = new ModelAndView("/survey/survey_list");
		mav.addObject("surveyList", surveyList);
		
		return mav;
	}

}
