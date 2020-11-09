package sion.bookmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ServerContext;

@Slf4j
public class ConnectionManager {
	private static ConnectionManager connectionManager = new ConnectionManager();
	private ConnectionManager() {
	}
	
	public static ConnectionManager getInstance() {
		return connectionManager;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = ServerContext.getDbUrl();
			String id = ServerContext.getDbId();
			String password = ServerContext.getDbPassword();
			
			Connection connection = DriverManager.getConnection(url, id, password);
			log.info("Connected to DB!");
			return connection;
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("드라이버 호출 실패", e);
		} catch(Exception e) {
			throw new RuntimeException("에러", e);
		} 
	}
	
	//TODO 구현
	public static DataSource getDataSource() {
//		Class.forName("com.mysql.jdbc.Driver");
//		
//		String url = ServerContext.getDbUrl();
//		String id = ServerContext.getDbId();
//		String password = ServerContext.getDbPassword();
		return null;
	}
}
