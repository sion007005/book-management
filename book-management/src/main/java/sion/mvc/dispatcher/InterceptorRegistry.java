package sion.mvc.dispatcher;

import java.util.List;

public interface InterceptorRegistry { // 컨트롤러 팩토리와 비슷한 역할 
	void addInterceptor(Interceptor interceptor);
	List<Interceptor> getInterceptors();
}
