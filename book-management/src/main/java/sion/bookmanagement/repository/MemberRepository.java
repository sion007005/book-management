package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.ConnectionManager;
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
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO members(name, gender, email, age, phone, password, salt, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			if(pstm != null) {
				try {
					pstm.close();
					conn.close();
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
		if (condition.getSearchType().getColumnName().equals("name")) {
			query += "name like ?";
		} else if (condition.getSearchType().getColumnName().equals("email")) {
			query += "email like ?";
		} else if (condition.getSearchType().getColumnName().equals("phone")) {
			query += "phone like ?";
		}
		
		query += " AND age between ? and ?"; 
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
			if(pstm != null) {
				try {
					pstm.close();
					conn.close();
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
			conn = ConnectionManager.getInstance().getConnection();
			String query = "UPDATE members SET name=?, gender=?, email=?, age=?, phone=?, password=?, created_at=?, updated_at=? WHERE member_id=?";
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
			if (pstm != null) {
				try {
					pstm.close();
					conn.close();
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
			conn = ConnectionManager.getInstance().getConnection();
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
					conn.close();
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
		String query = "SELECT member_id, name, gender, email, age, phone, password, created_at, updated_at FROM MEMBERS";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			if (orderType != null) {
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
		      	conn.close();
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
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT member_id, name, gender, email, age, phone, password, created_at, updated_at FROM members WHERE member_id = ?";
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
					conn.close();
				} catch (SQLException e) {
					throw new DataProcessException(e);
				}
			}
		}
		
		return null;
	}
	
	public Member findOneByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT member_id, password, salt FROM members WHERE email = ?";
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
			if (pstm != null) {
				try {
					pstm.close();
					conn.close();
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

}
