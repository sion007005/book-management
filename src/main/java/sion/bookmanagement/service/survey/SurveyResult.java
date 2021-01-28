package sion.bookmanagement.service.survey;

import java.util.List;

import lombok.Getter;

@Getter
public class SurveyResult {
	private Survey survey;
	private List<SurveyResultRow> surveyResultRows;
	
	public SurveyResult(Survey survey, List<SurveyResultRow> surveyResultRows) {
		this.survey = survey;
		this.surveyResultRows = surveyResultRows;
	}
}
