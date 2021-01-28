package sion.bookmanagement.service.survey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Getter
@Setter
public class SurveyResultRow {
	private Question question;
   private List<AnswerPair> answersPerRow = Arrays.asList(
           new AnswerPair(1, 0),
           new AnswerPair(2, 0),
           new AnswerPair(3, 0),
           new AnswerPair(4, 0),
           new AnswerPair(5, 0)
   );
   private Map<Integer, Integer> answerPairMap = new HashMap<>();
   
   public SurveyResultRow(Question question) {
   	this.question = question;
   }
   
   @Getter
   @Setter
   public class AnswerPair {
		private int order;
		private int count;
//	   	private score;
		
		public AnswerPair(int order, int count) {
			this.order = order;
			this.count = count;
		}
   }
   
   public void addCount(Answer answer) {
   	for (AnswerPair answerPair : answersPerRow) {
   		if (answerPair.order == answer.getCheckedNumber()) {
   			answerPair.count++;
   		}
   	}
   }
   

   
}


