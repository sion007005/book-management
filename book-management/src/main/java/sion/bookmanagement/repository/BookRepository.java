package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sion.bookmanagement.ConnectionManager;
import sion.bookmanagement.service.Book;
import sion.bookmanagement.service.BookOrderType;
import sion.bookmanagement.service.BookSearchCondition;
import sion.bookmanagement.util.DateUtils;

public class BookRepository {
	private static BookRepository bookRepository = new BookRepository();

	private BookRepository() {				
	}
	
	public static BookRepository getInstance() {
		return bookRepository;
	}
	
	public int create(Book book) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "INSERT INTO BOOKS(category_id, title, author, stock, year, price, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, book.getCategoryId());
			pstm.setString(2, book.getTitle());
			pstm.setString(3, book.getAuthor());
			pstm.setInt(4, book.getStock());
			pstm.setInt(5, book.getYear());
			pstm.setInt(6, book.getPrice());
			pstm.setTimestamp(7, DateUtils.getTimestamp(book.getCreatedAt()));
			pstm.setTimestamp(8, DateUtils.getTimestamp(book.getUpdatedAt()));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			rs.next();
			
			return rs.getInt(1); 
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
	
	public List<Book> search(BookSearchCondition condition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		String query = "SELECT book_id, category_id, title, author, stock, year, price, created_at, updated_at FROM BOOKS where ";
		if (condition.getSearchType().getColumnName().equals("title")) {
			query += "title like ?";
		} else if (condition.getSearchType().getColumnName().equals("author")) {
			query += "author like ?";
		} 
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%" + condition.getKeyword() + "%");
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				Book book = newBook(rs);
				bookList.add(book);
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
		
		return bookList;
	}
	
	public void update(Book book) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "update books set title=?, author=?, stock=?, year=?, price=?, created_at=?, updated_at=? where book_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setInt(3, book.getStock());
			pstm.setInt(4, book.getYear());
			pstm.setInt(5, book.getPrice());
			pstm.setTimestamp(6, DateUtils.getTimestamp(book.getCreatedAt()));
			pstm.setTimestamp(7, DateUtils.getTimestamp(book.getUpdatedAt()));
			pstm.setInt(8, book.getId());
			
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
	
	public void deleteById(Integer bookId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "delete from books where book_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bookId);
			
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
	
	public List<Book> findAll(BookOrderType orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		String query = "SELECT book_id, category_id, title, author, stock, year, price, created_at, updated_at FROM BOOKS"; 
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			if (orderType != null) {
				query += (" ORDER BY " + orderType);
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Book book = newBook(rs);
				bookList.add(book);
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
		
		return bookList;
	}

	public List<Book> findAllCategoryBooks(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "select book_id, category_id, title, author, stock, year, price, created from books where category_id=?";
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setStock(rs.getInt("stock"));
				book.setYear(rs.getInt("year"));
				book.setPrice(rs.getInt("price"));
				
				bookList.add(book);
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
		
		return bookList;
	}
	
	public Book findOneById(Integer bookId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "select book_id, category_id, title, author, stock, year, price, created_at, updated_at from books where book_id=?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bookId);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				Book book = newBook(rs);
				return book;
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
	
	private Book newBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		
		book.setCategoryId(rs.getInt("category_id"));
		book.setId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setStock(rs.getInt("stock"));
		book.setYear(rs.getInt("year"));
		book.setPrice(rs.getInt("price"));
		book.setCreatedAt(rs.getTimestamp("created_at"));
		book.setUpdatedAt(rs.getTimestamp("updated_at"));
		
		return book;
	}
}
