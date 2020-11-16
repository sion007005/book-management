package sion.mvc.auth;

import java.util.Objects;

public class UserContext {
	private static final ThreadLocal<User> THREAD_LOCAL;
	static {
		THREAD_LOCAL = new ThreadLocal<>();
	}
	
	public static User get() {
		return THREAD_LOCAL.get();
	}
	
	public static void set(User user) {
		THREAD_LOCAL.set(user);
	}
	
	public static void remove() {
		THREAD_LOCAL.remove();
	}
	
	public static boolean isLogin() {
		User user = get();
		
		if (Objects.isNull(user)) {
			return false;
		}
		
		return user.isLogin();
	}
	
	public static boolean isNotLogin() {
		return !isLogin();
	}
}