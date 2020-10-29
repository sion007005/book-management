package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.service.Member;
import sion.bookmanagement.service.MemberOrderType;
import sion.bookmanagement.service.MemberSearchCondition;
import sion.mvc.DBConnetctionCreator;

public class MemberRepository {
	private static MemberRepository memberRepository = new MemberRepository();
	
	private MemberRepository() {
	}
	
	public static MemberRepository getInstance() {
		return memberRepository ;
	}
	
	public int create(Member member) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "INSERT INTO MEMBERS(name, gender, email, age, phone) VALUES(?, ?, ?, ?, ?)";

			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			//TODO created 추가
			pstm.executeUpdate(); 
			
			rs = pstm.getGeneratedKeys();  
			rs.next();  
			int id = rs.getInt(1); //아이디 얻기  
			return id;
		} catch(SQLException e) {
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
	}
	
	public List<Member> search(MemberSearchCondition condition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		//TODO search type이 넘어오지 않았을 때의 이슈 해결
		String query = "SELECT member_id, name, gender, email, age, phone FROM MEMBERS where ";
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
			
			while(rs.next()) {
				Member member = newMember(rs);
				memberList.add(member);
			}
		} catch(SQLException e) {
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
			String query = "UPDATE members set name=?, gender=?, email=?, age=?, phone=? where member_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getName());
			pstm.setString(2, member.getGender());
			pstm.setString(3, member.getEmail());
			pstm.setInt(4, member.getAge());
			pstm.setString(5, member.getPhone());
			pstm.setInt(6, member.getId());
			
			pstm.executeUpdate();
		} catch(SQLException e) {
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
	}
	
	public void deleteById(Integer memberId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "DELETE FROM MEMBERS WHERE member_id" + "= ?";
			
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, memberId);
			pstm.executeUpdate();
		} catch(SQLException e) {
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
	}
	
	public List<Member> findAll(MemberOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT member_id, name, gender, email, age, phone FROM MEMBERS";
		
		List<Member> memberList = new ArrayList<Member>();
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			//TODO 한칸 띄우는거 찾아서 고치기
			if (orderType != null) {
				//TODO 쿼리문 수정 (쿼리문에 + 문자열을 해야할 때는 구조적으로 정해진 문자 말고는 들어오는 일이 없도록 해줘야 함)
				query += (" ORDER BY " + orderType.getColumnName());
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
//				Member member = new Member();
//				member.setId(rs.getInt("member_id"));
//				member.setName(rs.getString("name"));
//				member.setGender(rs.getString("gender"));
//				member.setEmail(rs.getString("email"));
//				member.setAge(rs.getInt("age"));
//				member.setPhone(rs.getString("phone"));
				//TODO created 기능 넣기 
				//member.setCreated(rs.getDate("created"));
				
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
			//TODO * 없애고 다 써주기
			String query = "SELECT member_id, name, gender, email, age, phone FROM MEMBERS WHERE member_id = ?";
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
		//TODO created도 세팅하기
		return member;
	}
}
