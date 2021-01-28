package sion.bookmanagement.controller.survey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.survey.SurveyResult;
import sion.bookmanagement.service.survey.SurveyResultService;
import sion.bookmanagement.service.survey.SurveyService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
public class SurveyResultController implements Controller {
	private SurveyService surveyService = SurveyService.getInstance();
	private SurveyResultService surveyResultService = SurveyResultService.getInstance();

	@Override
	@GetMapping("/survey/result")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int surveyIdx = NumberUtils.parseInt(request.getParameter("surveyIdx"));
		
//		Survey survey = surveyService.findOne(surveyIdx);
//		List<SurveyResultRow> surveyResultList = surveyResultService.getSurveyResultList(surveyIdx);
		
		SurveyResult surveyResult = surveyResultService.getSurveyResult(surveyIdx);
		ModelAndView mav = new ModelAndView("/survey/survey_result");
//		mav.addObject("survey", survey);
//		mav.addObject("surveyResultList", surveyResultList);
		mav.addObject("surveyResult", surveyResult);
		
		return mav;
	}

}
