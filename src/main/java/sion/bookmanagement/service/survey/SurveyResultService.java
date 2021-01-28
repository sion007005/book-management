package sion.bookmanagement.service.survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultService {
	private static SurveyResultService surveyResultService = new SurveyResultService();
	private AnswerService answerService = AnswerService.getInstance(); 
	private QuestionService questionService = QuestionService.getInstance();
	private SurveyService surveyService = SurveyService.getInstance();
	
	private SurveyResultService() {}

	public static SurveyResultService getInstance() {
		return surveyResultService;
	}
	
	public List<SurveyResultRow> getSurveyResultList(int surveyIdx) {
		List<SurveyResultRow> surveyResultList = new ArrayList<>();
		List<Question> questions = questionService.findAllBySurvey(surveyIdx);
		
		for (Question question : questions) {
			List<Answer> answers = answerService.findAllByQuestion(surveyIdx, question.getIdx());
			
			SurveyResultRow resultRow = makeResultRow(question, answers);
			surveyResultList.add(resultRow);
		}
		
		
		return surveyResultList;
	}

	private SurveyResultRow makeResultRow(Question question, List<Answer> answers) {
		SurveyResultRow resultRow = new SurveyResultRow(question);
		
		for (Answer answer : answers) {
			resultRow.addCount(answer);
		}
		
		return resultRow;
	}

	public SurveyResult getSurveyResult(int surveyIdx) {
		Survey survey = surveyService.findOne(surveyIdx);
		List<SurveyResultRow> surveyResultRows = surveyResultService.getSurveyResultList(surveyIdx);
		
		return new SurveyResult(survey, surveyResultRows);
	}

}
