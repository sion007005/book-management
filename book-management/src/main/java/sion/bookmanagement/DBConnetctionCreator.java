package sion.bookmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

import sion.mvc.ServerContext;

public class DBConnetctionCreator {
	private static DBConnetctionCreator dbConnetctionCreator = new DBConnetctionCreator();
	private DBConnetctionCreator() {
	}
	
	public static DBConnetctionCreator getInstance() {
		return dbConnetctionCreator;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = ServerContext.getDbUrl();
			String id = ServerContext.getDbId();
			String password = ServerContext.getDbPassword();
			
			Connection connection = DriverManager.getConnection(url, id, password);
			System.out.println("Connected to DB!");
			return connection;
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("드라이버 호출 실패", e);
		} catch(Exception e) {
			throw new RuntimeException("에러", e);
		} 
	}
}
