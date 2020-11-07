package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.ConnectionManager;
import sion.bookmanagement.service.Category;
import sion.bookmanagement.service.CategoryOrderType;
import sion.bookmanagement.service.CategorySearchCondition;
import sion.bookmanagement.util.DateUtils;

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
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO categories(category_name, created_at, updated_at) VALUES(?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, category.getName());
			pstm.setTimestamp(2, DateUtils.getTimestamp(category.getCreatedAt()));
			pstm.setTimestamp(3, DateUtils.getTimestamp(category.getUpdatedAt()));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();  
			rs.next();  
			
			return rs.getInt(1); //아이디 얻기  
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
	
	public void update(Category category) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "UPDATE categories SET category_name=?, created_at=?, updated_at=? WHERE category_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, category.getName());
			pstm.setTimestamp(2, DateUtils.getTimestamp(category.getCreatedAt()));
			pstm.setTimestamp(3, DateUtils.getTimestamp(category.getUpdatedAt()));
			pstm.setInt(4, category.getId());
			
			pstm.executeUpdate();
		} catch(SQLException e) {
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
	
	public void deleteById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "DELETE FROM categories WHERE category_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, categoryId);
			
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
	
	public List<Category> findAll(CategoryOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		String query = "SELECT category_id, category_name, created_at, updated_at FROM categories";
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			if (orderType != null) {
				query += (" ORDER BY " + orderType.getColumnName());
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Category category = newCategory(rs);
				categoryList.add(category);
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
		
		return categoryList;
	}
	
	public Category findOneById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT category_id, category_name, created_at, updated_at FROM categories WHERE category_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, categoryId);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				Category category = newCategory(rs);
				return category;
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
	
	public List<Category> search(CategorySearchCondition condition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		String query = "SELECT category_id, category_name, created_at, updated_at FROM CATEGORIES where category_name like ?";
				
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+ condition.getKeyword() +"%");
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				Category category = newCategory(rs);
				categoryList.add(category);
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
		
		return categoryList;
	}
	
	private Category newCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("category_id"));
		category.setName(rs.getString("category_name"));
		category.setCreatedAt(rs.getTimestamp("created_at"));
		category.setUpdatedAt(rs.getTimestamp("updated_at"));
		
		return category;
	}
}