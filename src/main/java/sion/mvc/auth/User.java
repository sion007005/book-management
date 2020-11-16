package sion.mvc.auth;

public interface User {
	boolean isLogin();
	Integer getMemberId();
	String getUserName();
	String getName();
	String getAccessIp();
}
