package sion.bookmanagement.service.survey;

import java.util.List;

import sion.bookmanagement.repository.survey.QuestionRepository;

public class QuestionService {
	private static QuestionService questionService = new QuestionService();
	private QuestionRepository questionRepository = QuestionRepository.getInstance();
	
	private QuestionService() {}
	
	public static QuestionService getInstance() {
		return questionService;
	}
	
	public int create(Question question) {
		return questionRepository.create(question);
	}
	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public List<Question> findAllBySurvey(int surveyIdx) {
		return questionRepository.findAllBySurvey(surveyIdx);
	}
}
