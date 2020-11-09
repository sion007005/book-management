package sion.bookmanagement.auth;

import java.util.Objects;

import lombok.Getter;
import sion.mvc.auth.User;

@Getter //변수에 대한 getter 자동 생성해줌 
/* 
 * 접속한 사용자 정보
 * immutable 객체(처음 생성할 때 데이터를 넣은 후엔 수정할 수 없도록 함, setter 없음)  
 */
public class BookManagementUser implements User { 
	private Integer memberId;
	private String userName;
	private String name;
	private String accessIp;
	
	private BookManagementUser(Integer memberId, String userName, String name, String accessIp) {
		this.memberId = memberId;
		this.userName = userName;
		this.name = name;
		this.accessIp = accessIp;
	}
	
	public static BookManagementUser newLogoutUser(String accessIp) {
		return new BookManagementUser(null, null, null, accessIp);
	}
	
	public static BookManagementUser newLoginUser(Integer memberId, String userName, String name, String accessIp) {
		return new BookManagementUser(memberId, userName, name, accessIp);
	}
	
	public boolean isLogin() {
		if (Objects.isNull(memberId)) {
			return false;
		}
		
		return true;
	}
}
