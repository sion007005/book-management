package sion.mvc;

import java.sql.Connection;
import java.sql.DriverManager;

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
			
			String url = "jdbc:mysql://localhost/siondb";
			Connection connection = DriverManager.getConnection(url, "sion", "1234");
			System.out.println("Connected to DB!");
			return connection;
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("드라이버 호출 실패", e);
		} catch(Exception e) {
			throw new RuntimeException("에러", e);
		} 
	}
}
