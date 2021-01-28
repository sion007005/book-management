package sion.bookmanagement.controller.survey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.survey.Question;
import sion.bookmanagement.service.survey.QuestionService;
import sion.bookmanagement.service.survey.Survey;
import sion.bookmanagement.service.survey.SurveyService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.auth.User;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.Login;
import sion.mvc.render.ViewRender;

public class SurveyJoinController implements Controller {
	private SurveyService surveyService = SurveyService.getInstance();
	private QuestionService questionService = QuestionService.getInstance();
	
	@Login
	@Override
	@GetMapping("/survey/join")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int surveyIdx = NumberUtils.parseInt(request.getParameter("surveyIdx"));
		Survey survey = surveyService.findOne(surveyIdx);
		
		if (survey.getIsClosed() == 1) {
			ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/survey/list");
			mav.addObject("message", "마감된 설문입니다.");
			
			return mav;
		} 
		
		User user = UserContext.get();
		List<Question> questionList = questionService.findAllBySurvey(surveyIdx);
		ModelAndView mav = new ModelAndView("/survey/survey_join_form");
		mav.addObject("survey", survey);
		mav.addObject("questionList", questionList);
		mav.addObject("memberIdx", user.getMemberId());
		
		return mav;
	}

}
