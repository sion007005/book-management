package sion.mvc.auth;

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
		//TODO CHECK 멤버가 없을 때도 항상 로그아웃 유저가 등록되므로 이거는 필요없는 코드가 아닌가?
//		if (Objects.isNull(user)) {
//			System.out.println("there is no User!!!");
//			return false;
//		}
//		
		return user.isLogin();
	}
	
	public static boolean isNotLogin() {
		return !isLogin();
	}
}
