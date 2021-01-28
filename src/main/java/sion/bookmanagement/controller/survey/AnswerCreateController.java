package sion.bookmanagement.controller.survey;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.service.survey.Answer;
import sion.bookmanagement.service.survey.SurveyService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.ModelAndView;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;

public class AnswerCreateController implements Controller {
	private SurveyService surveyService = SurveyService.getInstance();

	@Override
	@PostMapping("/survey/answer/create")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		int memberIdx = UserContext.get().getMemberId();
		int surveyIdx = NumberUtils.parseInt(request.getParameter("surveyIdx"));
		int questionSize = NumberUtils.parseInt(request.getParameter("questionSize"));
		List<Answer> answerList = new ArrayList<>();
		
		for (int i = 1; i <= questionSize; i++) {
			int questionIdx = NumberUtils.parseInt(request.getParameter("question" + i));
			int checkedNumber = NumberUtils.parseInt(request.getParameter("answer" + i));
			
			Answer answer = new Answer(memberIdx, surveyIdx, questionIdx, checkedNumber);
			answerList.add(answer);
//			answerService.create(answer);
		}
//		surveyService.addOneJoinCount(surveyIdx);

		surveyService.registerAnswerList(surveyIdx, answerList);
		
		ModelAndView mav = new ModelAndView(ViewRender.REDIRECT_NAME + "/survey/list");
		return mav;
	}

}
