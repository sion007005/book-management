package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.service.Category;
import sion.mvc.DBConnetctionCreator;

public class CategoryRepository {
	private static CategoryRepository categoryRepository = new CategoryRepository();
	  
	private CategoryRepository() {		
	}
	
	public static CategoryRepository getInstance() {
		return categoryRepository ;
	}
	
	public int create(Category category) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "INSERT INTO categories(category_name) VALUES(?)";
	
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, category.getName());
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();  
			rs.next();  
			return rs.getInt(1); //아이디 얻기  
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
	
	public void update(Category category) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "update categories set category_name=? where category_id=?";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, category.getName());
			pstm.setInt(2, category.getId());
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
	
	public void deleteById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "delete from categories where category_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, categoryId);
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
	
	public List<Category> findAll(String orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "select * from categories";
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			if(orderType != null) {
				query += (" ORDER BY " + orderType);
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				categoryList.add(category);
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
		
		return categoryList;
	}
	
	public Category findOneById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "select * from categories where category_id = ?";
			pstm = conn.prepareStatement(query);

			pstm.setInt(1, categoryId);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
			
				return category;
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
		return null;
	}

	public List<Category> searchById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		String query = "SELECT category_id, category_name FROM CATEGORIES where category_id = ?";
				
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				
				
				categoryList.add(category);
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
		
		return categoryList;
	}
	
	public List<Category> searchByKeyword(String keyword) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		String query = "SELECT category_id, category_name FROM CATEGORIES where category_name like ?";
				
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+keyword+"%");
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				
				
				categoryList.add(category);
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
		
		return categoryList;
	}
}