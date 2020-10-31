package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.DBConnetctionCreator;
import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberOrderType;
import sion.bookmanagement.service.MemberSearchCondition;
import sion.bookmanagement.util.DateUtils;

public class MemberRepository {
	private static MemberRepository memberRepository = new MemberRepository();
	
	private MemberRepository() {
	}
	
	public static MemberRepository getInstance() {
		return memberRepository;
	}
	
	public int create(Member member) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "INSERT INTO members(name, gender, email, age, phone, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			pstm.setTimestamp(6, DateUtils.getTimestamp(member.getCreatedAt()));
			pstm.setTimestamp(7, DateUtils.getTimestamp(member.getUpdatedAt()));
			pstm.executeUpdate(); 
			
			rs = pstm.getGeneratedKeys();  
			rs.next();  
			
			return rs.getInt(1); //아이디 얻기  
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			if(pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					throw new DataProcessException(e);
				}
			}
		}
	}
	
	public List<Member> search(MemberSearchCondition condition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		String query = "SELECT member_id, name, gender, email, age, phone, created_at, updated_at FROM members WHERE ";
		if (condition.getSearchType().equals("name")) {
			query += "name like ?";
		} else if (condition.getSearchType().equals("email")) {
			query += "email like ?";
		} else if (condition.getSearchType().equals("phone")) {
			query += "phone like ?";
		}
		
		query += " AND age between ? and ?"; 
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+ condition.getKeyword() +"%");
			pstm.setInt(2, condition.getAgeFrom());
			pstm.setInt(3, condition.getAgeTo());
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				Member member = newMember(rs);
				memberList.add(member);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			if(pstm != null) {
				try {
					pstm.close();
				} catch(SQLException e) {
					throw new DataProcessException(e);
				}
			}
		}
		
		return memberList;
	}
	
	public void update(Member member) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "UPDATE members SET name=?, gender=?, email=?, age=?, phone=?, created_at=?, updated_at=? WHERE member_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			pstm.setTimestamp(6, DateUtils.getTimestamp(member.getCreatedAt()));
			pstm.setTimestamp(7, DateUtils.getTimestamp(member.getUpdatedAt()));
			pstm.setInt(8, member.getId());
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch(SQLException e) {
					throw new DataProcessException(e);
				}
			}
		}
	}
	
	public void deleteById(Integer memberId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "DELETE FROM members WHERE member_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, memberId);
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					throw new DataProcessException(e);
				}
			}
		}
	}
	
	public List<Member> findAll(MemberOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Member> memberList = new ArrayList<Member>();
		String query = "SELECT member_id, name, gender, email, age, phone, created_at, updated_at FROM MEMBERS";
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			if (orderType != null) {
				//TODO 쿼리문 수정 (쿼리문에 + 문자열을 해야할 때는 구조적으로 정해진 문자 말고는 들어오는 일이 없도록 해줘야 함)
				query += (" ORDER BY " + orderType.getColumnName());
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Member member = newMember(rs);
				memberList.add(member);
			}
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
		   if (pstm != null) {
		      try {
		      	pstm.close();
		      } catch (SQLException e) {
		      	throw new DataProcessException(e);
		      }
		   }
		}
		
		return memberList;
	}
	
	public Member findOneById(Integer memberId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "SELECT member_id, name, gender, email, age, phone, created_at, updated_at FROM members WHERE member_id = ?";
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
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					throw new DataProcessException(e);
				}
			}
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
		member.setCreatedAt(rs.getTimestamp("created_at"));
		member.setUpdatedAt(rs.getTimestamp("updated_at"));
		
		return member;
	}
}
