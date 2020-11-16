package sion.bookmanagement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRepository {
	//protected는 상속까지 접근가능
	protected void closeDbResource(Connection conn, PreparedStatement pstm) {
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}
