package sion.bookmanagement.service.survey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
	private int idx;
	private int surveyIdx;
	private String content;
	
	public Question() {}
	
	public Question(int surveyIdx, String content) {
		this.surveyIdx = surveyIdx;
		this.content = content;
	}
}
