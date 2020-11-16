package sion.mvc.dispatcher;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import sion.http.HttpRequest;
import sion.http.HttpResponse;
import sion.http.HttpStatus;
import sion.mvc.ApplicationContext;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
import sion.mvc.render.JsonViewRender;

@Slf4j
public class Dispatcher {
	private static Dispatcher dispatcher = new Dispatcher();
	
	private ControllerFactory controllerFactory;
	private InterceptorRegistry interceptorRegistry;
	
	private Dispatcher() {
		controllerFactory = ApplicationContext.getControllerFactory();
		interceptorRegistry = ApplicationContext.getInterceptorRegistry();
	}

	public static Dispatcher getInstance() {
		return dispatcher;
	}

	/*
	 * 1. 요청 url에 맞는 controller를 찾아서 실행한다.
	 * 2. status code를 최종적으로 결정한다.
	 */
	public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
//		try {
//			// 정적 리소스를 처리하는 로직
//			if (isStaticResourceRequest(httpRequest)) {
//				ViewRender viewRender = new StaticResourceViewRender();
//				viewRender.render(httpRequest, httpResponse, null);
//				
//				return;
//			}
//			
//			Controller controller = controllerFactory.getInstance(controllerFactory.getKey(httpRequest));
//			
//			preCommand(controller, httpRequest, httpResponse);
//			
//			ModelAndView mav = controller.command(httpRequest, httpResponse);
//			httpResponse.setModelAndView(mav);
//			makeStatus(httpResponse, mav);
//			
//			postCommand(controller, httpRequest, httpResponse);
//			
//			render(httpRequest, httpResponse, mav);
//		} catch (ForbiddenException e) {
//			log.error(e.getMessage(), e);
//			
//			ModelAndView mav = new ModelAndView("error/forbidden");
//			mav.addObject("_error_message", e.getMessage());
//			httpResponse.setHttpStatus(HttpStatus.FORBIDDEN);
//			
//			render(httpRequest, httpResponse, mav);
//		} catch (FileNotFoundException e) {
//			log.error(e.getMessage(), e);
//			
//			ModelAndView mav = new ModelAndView("error/not_found");
//			mav.addObject("_error_message", e.getMessage());
//			httpResponse.setHttpStatus(HttpStatus.NOT_FOUND);
//			
//			render(httpRequest, httpResponse, mav);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			
//			ModelAndView mav = new ModelAndView("error/server_error");
//			mav.addObject("errorMessage", e.getMessage());
//			httpResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//			
//			render(httpRequest, httpResponse, mav);
//		}
		
	}
	
	private boolean isStaticResourceRequest(HttpRequest httpRequest) {
		List<String> pathList = ApplicationContext.getStaticResourcePathList();
		for (String path : pathList) {
			if (httpRequest.getUriPath().startsWith(path)) {
				return true;
			}
		}
		
		return false;
	}

	private void preCommand(Controller controller, HttpRequest httpRequest, HttpResponse httpResponse) throws DispatcherException {
		List<Interceptor> interceptors = interceptorRegistry.getInterceptors();
		if (Objects.isNull(interceptors)) {
			return;
		}
		
		for (Interceptor interceptor : interceptors) {
			// 인터셉터에 등록된 순서대로 실행된다.
//			if (!interceptor.preHandle(httpRequest, httpResponse, controller)) {
//				throw new DispatcherException(interceptor + ": interceptor return false");
//			}
		}
	}

	private void makeStatus(HttpResponse httpResponse, ModelAndView mav) {
		if (mav.getViewName() == null) {
			return;
		}
		
		if (mav.getViewName().startsWith(HttpResponse.REDIRECT_NAME)) {
			httpResponse.setHttpStatus(HttpStatus.MOVED_PERMANENTLY);
			return;
		} 			
		
		httpResponse.setHttpStatus(HttpStatus.OK);
	}

	private void postCommand(Controller controller, HttpRequest httpRequest, HttpResponse httpResponse) {
		List<Interceptor> interceptors = interceptorRegistry.getInterceptors();
		if (Objects.isNull(interceptors)) {
			return;
		}
		
		// 인터셉터 등록된 순서와 거꾸로 실행한다.
		for (int i = interceptors.size() - 1; i >= 0; i--) {
			Interceptor interceptor = interceptors.get(i);
//			interceptor.postHandle(httpRequest, httpResponse, controller);
		}
	}

	private void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		if (HttpResponse.JSON_VIEW_NAME.equals(mav.getViewName())) {
			ViewRender viewRender = new JsonViewRender();
//			viewRender.render(httpRequest, httpResponse, mav);
			return;
		}
		
//		ViewRender responseProcessor = FreemarkerViewRenderFactory.getInstance(httpResponse.getHttpStatus()); 
//	   responseProcessor.render(httpRequest, httpResponse, mav);
	}
	
}
