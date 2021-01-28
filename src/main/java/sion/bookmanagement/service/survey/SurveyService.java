package sion.bookmanagement.service.survey;

import java.util.List;

import sion.bookmanagement.repository.survey.SurveyRepository;

public class SurveyService {
	private static SurveyService surveyService = new SurveyService();
	private SurveyRepository surveyRepository = SurveyRepository.getInstance(); 
	private AnswerService answerService = AnswerService.getInstance();
	
	private SurveyService() {}
	
	public static SurveyService getInstance() {
		return surveyService;
	}

	public int create(Survey survey) {
		return surveyRepository.create(survey);
	}
	
	public List<Survey> findAll() {
		return surveyRepository.findAll();
	}

	public Survey findOne(int surveyIdx) {
		return surveyRepository.findOne(surveyIdx);
	}
	
	public void addOneJoinCount(int surveyIdx) {
		surveyRepository.addOneJoinCount(surveyIdx);
	}

	
	public void registerAnswerList(int surveyIdx, List<Answer> answerList) {
		for (Answer answer : answerList) {
			answerService.create(answer);
		}
		
		addOneJoinCount(surveyIdx);		
	}
	
}
