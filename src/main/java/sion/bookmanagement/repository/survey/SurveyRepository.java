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
import sion.bookmanagement.service.survey.Survey;
import sion.bookmanagement.util.DateUtils;

public class SurveyRepository extends BaseRepository {
	private static SurveyRepository surveyRepository = new SurveyRepository();
	
	private SurveyRepository() {}
	
	public static SurveyRepository getInstance() {
		return surveyRepository;
	}
	
	public int create(Survey survey) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO survey(start_date, end_date, subject, target, is_closed) VALUES(?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setTimestamp(1, DateUtils.getTimestamp(survey.getStartDate()));
			pstm.setTimestamp(2, DateUtils.getTimestamp(survey.getEndDate()));
			pstm.setString(3, survey.getSubject());
			pstm.setString(4, survey.getTarget());
			pstm.setInt(5, survey.getIsClosed());
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
	
	public Survey findOne(int idx) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String query = "SELECT idx, start_date, end_date, subject, target, join_member_count, is_closed FROM survey where idx = ?";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, idx);
			rs = pstm.executeQuery();

			if (rs.next()) {
				Survey survey = new Survey();
				survey.setIdx(rs.getInt("idx"));
				survey.setStartDate(rs.getTimestamp("start_date"));
				survey.setEndDate(rs.getTimestamp("end_date"));
				survey.setSubject(rs.getString("subject"));
				survey.setTarget(rs.getString("target"));
				survey.setJoinMemberCount(rs.getInt("join_member_count"));
				survey.setIsClosed(rs.getInt("is_closed"));
				
				return survey;
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return null;
	}
	
	
	public List<Survey> findAll() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Survey> surveyList = new ArrayList<>();
		String query = "SELECT idx, start_date, end_date, subject, target, join_member_count, is_closed FROM survey Limit 10"; 
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Survey survey = new Survey();
				survey.setIdx(rs.getInt("idx"));
				survey.setStartDate(rs.getTimestamp("start_date"));
				survey.setEndDate(rs.getTimestamp("end_date"));
				survey.setSubject(rs.getString("subject"));
				survey.setTarget(rs.getString("target"));
				survey.setJoinMemberCount(rs.getInt("join_member_count"));
				survey.setIsClosed(rs.getInt("is_closed"));
				
				surveyList.add(survey);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		return surveyList;
	}
	
	public void addOneJoinCount(int surveyIdx) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "update survey set join_member_count = join_member_count + 1 where idx = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, surveyIdx);
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
}
