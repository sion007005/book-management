package sion.bookmanagement.controller.survey;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.survey.Question;
import sion.bookmanagement.service.survey.QuestionService;
import sion.bookmanagement.service.survey.Survey;
import sion.bookmanagement.service.survey.SurveyService;
import sion.bookmanagement.util.DateUtils;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

@Slf4j
public class SurveyCreateController implements Controller {
	private SurveyService surveyService = SurveyService.getInstance();
	private QuestionService questionService = QuestionService.getInstance();

	@Override
	@PostMapping("/survey/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		// Survey 생성
		Date startDate = DateUtils.getSimpleDate(request.getParameter("startDate")); 
		Date endDate = DateUtils.getSimpleDate(request.getParameter("endDate"));
		String subject = request.getParameter("subject");
		String target = request.getParameter("target");
		int isClosed = NumberUtils.parseInt(request.getParameter("isClosed"));
		
		Survey survey = new Survey(startDate, endDate, subject, target, isClosed);
		int surveyIdx = surveyService.create(survey);
		
		// Question 생성
		for (int i = 1; i <= 8; i++) {
			String content = request.getParameter("question"+i);
			Question question = new Question(surveyIdx, content);
			questionService.create(question);
		}
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/survey/list");
		return mav;
	}

}
