package sion.bookmanagement.interceptor;

import java.util.ArrayList;
import java.util.List;

import sion.mvc.dispatcher.Interceptor;
import sion.mvc.dispatcher.InterceptorRegistry;

public class BookManagementInterceptorRegistry implements InterceptorRegistry {
	private static List<Interceptor> interceptors = new ArrayList<>();
	static {
		interceptors.add(new LoginInterceptor());
	}
	
	@Override
	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	@Override
	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

}
