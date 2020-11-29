package sion.bookmanagement.repository.member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mysql.jdbc.Statement;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.ConnectionManager;
import sion.bookmanagement.controller.Pagenation;
import sion.bookmanagement.repository.BaseRepository;
import sion.bookmanagement.repository.DataProcessException;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberOrderType;
import sion.bookmanagement.service.member.MemberSearchCondition;
import sion.bookmanagement.util.DateUtils;

@Slf4j
public class MemberRepository extends BaseRepository {
	private static MemberRepository memberRepository = new MemberRepository();
	
	public MemberRepository() {
	}
	
	public static MemberRepository getInstance() {
		return memberRepository;
	}
	
	public int create(Member member) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO member(name, gender, email, age, phone, password, salt, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			pstm.setString(6, member.getPassword());
			pstm.setString(7, member.getSalt());
			pstm.setTimestamp(8, DateUtils.getTimestamp(member.getCreatedAt()));
			pstm.setTimestamp(9, DateUtils.getTimestamp(member.getUpdatedAt()));
			pstm.executeUpdate(); 
			
			rs = pstm.getGeneratedKeys();  
			rs.next();  
			
			return rs.getInt(1); //아이디 얻기  
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}

	public List<Member> search(MemberSearchCondition condition, MemberOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Member> memberList = new ArrayList<Member>();
		
		String query = "SELECT member_id, name, gender, email, age, phone, created_at, updated_at FROM member ";
		query += " where " + condition.getSearchType().getColumnName() + " like ? ";
		query += " AND age between ? and ?";
		
		if (Objects.nonNull(orderType)) {
			query += (" ORDER BY " + orderType.getColumnName());
		}
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+ condition.getKeyword() +"%");
			pstm.setInt(2, condition.getAgeFrom());
			pstm.setInt(3, condition.getAgeTo());
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				Member member = newMemberWithoutPassword(rs);
				memberList.add(member);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return memberList;
	}
	
	public void update(Member member) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "UPDATE member SET name=?, gender=?, email=?, age=?, phone=?, password=?, created_at=?, updated_at=? WHERE member_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			pstm.setString(6, member.getPassword());
			pstm.setTimestamp(7, DateUtils.getTimestamp(member.getCreatedAt()));
			pstm.setTimestamp(8, DateUtils.getTimestamp(member.getUpdatedAt()));
			pstm.setInt(9, member.getId());
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
	
	public void deleteById(Integer memberId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "DELETE FROM members WHERE member_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, memberId);
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
	
	public List<Member> findAll(MemberOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Member> memberList = new ArrayList<Member>();
		String query = "SELECT member_id, name, gender, email, age, phone, password, created_at, updated_at FROM member";
		
		try {
			if (Objects.nonNull(orderType)) {
				query += (" ORDER BY " + orderType.getColumnName());
			}
			
			conn = ConnectionManager.getInstance().getConnection();
			query += " LIMIT " + Pagenation.startIndex + "," + Pagenation.ITEM_SIZE_PER_PAGE; 
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Member member = newMember(rs);
				memberList.add(member);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return memberList;
	}
	
	public Member findOneById(Integer memberId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT member_id, name, gender, email, age, phone, password, created_at, updated_at FROM member WHERE member_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, memberId);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				Member member = newMember(rs);
				return member;
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return null;
	}
	
	public Member findOneByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT member_id, password, salt FROM member WHERE email = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("member_id"));
				member.setPassword(rs.getString("password"));
				member.setSalt(rs.getString("salt"));
				return member;
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return null;
	}

	private Member newMember(ResultSet rs) throws SQLException {
		Member member = new Member();
		
		member.setId(rs.getInt("member_id"));
		member.setName(rs.getString("name"));
		member.setGender(rs.getString("gender"));
		member.setEmail(rs.getString("email"));
		member.setAge(rs.getInt("age"));
		member.setPhone(rs.getString("phone"));
		member.setPassword(rs.getString("password"));
		member.setCreatedAt(rs.getTimestamp("created_at"));
		member.setUpdatedAt(rs.getTimestamp("updated_at"));
		
		return member;
	}
	
	private Member newMemberWithoutPassword(ResultSet rs) throws SQLException {
		Member member = new Member();
		
		member.setId(rs.getInt("member_id"));
		member.setName(rs.getString("name"));
		member.setGender(rs.getString("gender"));
		member.setEmail(rs.getString("email"));
		member.setAge(rs.getInt("age"));
		member.setPhone(rs.getString("phone"));
		member.setCreatedAt(rs.getTimestamp("created_at"));
		member.setUpdatedAt(rs.getTimestamp("updated_at"));
		
		return member;
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT COUNT(*) as cnt FROM member");
			pstm = conn.prepareStatement(sql.toString());
			
			rs = pstm.executeQuery();
			if (rs.next()) { 
				count = rs.getInt("cnt"); 
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
		
		return count;
	}
}
