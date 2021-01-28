package sion.bookmanagement.repository.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sion.bookmanagement.ConnectionManager;
import sion.bookmanagement.repository.BaseRepository;
import sion.bookmanagement.repository.DataProcessException;
import sion.bookmanagement.service.survey.Answer;

public class AnswerRepository extends BaseRepository {
	private static AnswerRepository answerRepository = new AnswerRepository();
	
	private AnswerRepository() {
	}
	
	public static AnswerRepository getInstance() {
		return answerRepository;
	}
	
	public int create(Answer answer) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO answer(member_idx, survey_idx, question_idx, checked_number) VALUES(?, ?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, answer.getMemberIdx());
			pstm.setInt(2, answer.getSurveyIdx());
			pstm.setInt(3, answer.getQuestionIdx());
			pstm.setInt(4, answer.getCheckedNumber());
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			rs.next();
			
			return rs.getInt(1); 
		} catch(SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
	
	public List<Answer> findAllByQuestion(int surveyIdx, int questionIdx) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Answer> answers = new ArrayList<>();
		String query = "SELECT idx, member_idx, survey_idx, question_idx, checked_number FROM answer where (survey_idx = ? AND question_idx = ?)";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, surveyIdx);
			pstm.setInt(2, questionIdx);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Answer answer = new Answer();
				answer.setIdx(rs.getInt("idx"));
				answer.setMemberIdx(rs.getInt("member_idx"));
				answer.setSurveyIdx(rs.getInt("survey_idx"));
				answer.setQuestionIdx(rs.getInt("question_idx"));
				answer.setCheckedNumber(rs.getInt("checked_number"));
				
				answers.add(answer);
			}
		} catch(SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return answers;
	}
	
}
