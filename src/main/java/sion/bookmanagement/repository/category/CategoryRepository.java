package sion.bookmanagement.repository.category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import sion.bookmanagement.ConnectionManager;
import sion.bookmanagement.repository.BaseRepository;
import sion.bookmanagement.repository.DataProcessException;
import sion.bookmanagement.service.category.Category;
import sion.bookmanagement.service.category.CategoryOrderType;
import sion.bookmanagement.service.category.CategorySearchCondition;
import sion.bookmanagement.util.DateUtils;

public class CategoryRepository extends BaseRepository {
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
			String query = "INSERT INTO category(category_name, created_at, updated_at) VALUES(?, ?, ?)";
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
			closeDbResource(conn, pstm);
		}
	}
	
	public void update(Category category) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "UPDATE category SET category_name=?, created_at=?, updated_at=? WHERE category_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, category.getName());
			pstm.setTimestamp(2, DateUtils.getTimestamp(category.getCreatedAt()));
			pstm.setTimestamp(3, DateUtils.getTimestamp(category.getUpdatedAt()));
			pstm.setInt(4, category.getId());
			
			pstm.executeUpdate();
		} catch(SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
	
	public void deleteById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "DELETE FROM category WHERE category_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, categoryId);
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataProcessException(e);
		} finally {
			closeDbResource(conn, pstm);
		}
	}
	
	public List<Category> findAll(CategoryOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Category> categoryList = new ArrayList<Category>();
		String query = "SELECT category_id, category_name, created_at, updated_at FROM category";
		
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
			closeDbResource(conn, pstm);
		}
		
		return categoryList;
	}
	
	public Category findOneById(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT category_id, category_name, created_at, updated_at FROM category WHERE category_id = ?";
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
			closeDbResource(conn, pstm);
		}
		return null;
	}
	
	public List<Category> search(CategorySearchCondition condition, CategoryOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Category> categoryList = new ArrayList<Category>();
		
		String query = "SELECT category_id, category_name, created_at, updated_at FROM category where category_name like ?";
			
		if (orderType != null) {
			query += (" ORDER BY " + orderType);
		}
		
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
			closeDbResource(conn, pstm);
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