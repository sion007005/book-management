package sion.bookmanagement.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sion.bookmanagement.service.Book;
import sion.bookmanagement.util.DateUtils;
import sion.mvc.DBConnetctionCreator;

public class BookRepository {
	private static BookRepository bookRepository = new BookRepository();

	private BookRepository() {				
	}
	
	public static BookRepository getInstance() {
		return bookRepository;
	}
	
	public void create(Book book) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "INSERT INTO BOOKS(category_id, title, author, stock, year, price, created) VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, book.getCategoryId());
			pstm.setString(2, book.getTitle());
			pstm.setString(3, book.getAuthor());
			pstm.setInt(4, book.getStock());
			pstm.setInt(5, book.getYear());
			pstm.setInt(6, book.getPrice());
			pstm.setDate(7, DateUtils.getSqlDate(book.getCreatedDate()));
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
	
	public void update(Book book) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "update books set title=?, author=?, stock=?, year=?, price=?, created=? where book_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setInt(3, book.getStock());
			pstm.setInt(4, book.getYear());
			pstm.setInt(5, book.getPrice());
			pstm.setDate(6, DateUtils.getSqlDate(book.getCreatedDate()));
			pstm.setInt(7, book.getId());
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
	
	public void deleteById(Integer bookId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "delete from books where book_id=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, bookId);
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
	
	public List<Book> findAllBooks(String orderType) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		String query = "SELECT book_id, category_id, title, author, stock, year, price, created FROM BOOKS"; 
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			if(orderType != null) {
				query += (" ORDER BY " + orderType);
			}
			
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
			
				book.setId(rs.getInt("book_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setStock(rs.getInt("stock"));
				book.setYear(rs.getInt("year"));
				book.setPrice(rs.getInt("price"));
				book.setCreatedDate(rs.getDate("created"));
				
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

	public List<Book> findAllCategoryBooks(Integer categoryId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
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
			conn = DBConnetctionCreator.getInstance().getConnection();
			String query = "select book_id, category_id, title, author, stock, year, price, created from books where book_id=?";
			pstm = conn.prepareStatement(query);

			pstm.setInt(1, bookId);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				Book book = new Book();
				book.setCategoryId(rs.getInt("category_id"));
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setStock(rs.getInt("stock"));
				book.setYear(rs.getInt("year"));
				book.setPrice(rs.getInt("price"));
				book.setCreatedDate(rs.getDate("created"));
				return book;
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

	public List<Book> searchByKeyword(String searchType, String keyword) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		String query = "SELECT book_id, category_id, title, author, stock, year, price FROM BOOKS where ";
		if(searchType.equals("title")) {
			query += "title like ?";
		} else if(searchType.equals("author")) {
			query += "author like ?";
		} 
		
		try {
			conn = DBConnetctionCreator.getInstance().getConnection();
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+keyword+"%");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				
				book.setId(rs.getInt("book_id"));
				book.setCategoryId(rs.getInt("category_id"));
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

}
