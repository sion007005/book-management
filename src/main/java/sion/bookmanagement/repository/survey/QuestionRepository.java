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
import sion.bookmanagement.service.survey.Question;

public class QuestionRepository extends BaseRepository {
	private static QuestionRepository questionRepository = new QuestionRepository();
	
	private QuestionRepository() {}

	public static QuestionRepository getInstance() {
		return questionRepository;
	}
	
	public int create(Question question) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO question(survey_idx, content) VALUES(?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, question.getSurveyIdx());
			pstm.setString(2, question.getContent());
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
	
	public List<Question> findAllBySurvey(int surveyIdx) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Question> questionList = new ArrayList<>();
		String query = "SELECT idx, survey_idx, content FROM question where survey_idx = ?";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, surveyIdx);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Question question = new Question();
				question.setIdx(rs.getInt("idx"));
				question.setSurveyIdx(rs.getInt("survey_idx"));
				question.setContent(rs.getString("content"));
				
				questionList.add(question);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return questionList;
	}
	
	public List<Question> findAll() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Question> questionList = new ArrayList<>();
		String query = "SELECT idx, survey_idx, content FROM question limit 10";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Question question = new Question();
				question.setIdx(rs.getInt("idx"));
				question.setSurveyIdx(rs.getInt("survey_idx"));
				question.setContent(rs.getString("content"));
				
				questionList.add(question);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return questionList;
	}
}
