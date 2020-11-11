package sion.bookmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.repository.DataProcessException;
import sion.mvc.ApplicationContext;

@Slf4j
public class ConnectionManager {
	private static ConnectionManager connectionManager = new ConnectionManager();
	private DataSource dataSource;
	
	private ConnectionManager() {
		if (Objects.isNull(dataSource)) {
			// pool을 한번만 생성하도록 한다.
			dataSource = getDataSource();
		}
	}
	
	public static ConnectionManager getInstance() {
		return connectionManager;
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new DataProcessException(e.getMessage(), e);
		}
	}
	
	 private synchronized DataSource getDataSource() {
		 //데이터 베이스 설정 넣기
		 // TODO 하드코딩된 글자들 설정파일로 빼기
		  HikariConfig hikariConfig = new HikariConfig();
		  hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		  hikariConfig.setJdbcUrl(ApplicationContext.getDbUrl());
		  hikariConfig.setUsername(ApplicationContext.getDbId());
		  hikariConfig.setPassword(ApplicationContext.getDbPassword());
		  // 커넥션 풀을 세개를 만들어 두고 재활용해라 
		  hikariConfig.setMaximumPoolSize(3);
		  hikariConfig.setConnectionTestQuery("SELECT 1");
		  hikariConfig.setPoolName("bookManagementHikariCP");
		  hikariConfig.setConnectionTimeout(3000);
		  
		  DataSource dataSource = new HikariDataSource(hikariConfig);
		  return dataSource;
		 }
	
	
}
