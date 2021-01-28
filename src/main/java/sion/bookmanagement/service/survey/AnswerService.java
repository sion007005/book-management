package sion.bookmanagement.service.survey;

import java.util.List;

import sion.bookmanagement.repository.survey.AnswerRepository;

public class AnswerService {
	private static AnswerService answerService = new AnswerService();
	private AnswerRepository answerRepository = AnswerRepository.getInstance();
	
	private AnswerService() {}
	
	public static AnswerService getInstance() {
		return answerService;
	}
	
	public int create(Answer answer) {
		return answerRepository.create(answer);
	}
	
	public List<Answer> findAllByQuestion(int surveyIdx, int questionIdx) {
		return answerRepository.findAllByQuestion(surveyIdx, questionIdx);
	}
	
}
