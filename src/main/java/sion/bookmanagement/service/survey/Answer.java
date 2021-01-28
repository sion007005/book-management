package sion.bookmanagement.service.survey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
	private int idx;
	private int memberIdx;
	private int surveyIdx;
	private int questionIdx;
	private int checkedNumber;
	
	public Answer() {}
	
	public Answer(int memberIdx, int surveyIdx, int questionIdx, int checkedNumber) {
		this.memberIdx = memberIdx;
		this.surveyIdx = surveyIdx;
		this.questionIdx = questionIdx;
		this.checkedNumber = checkedNumber;
	}
}
