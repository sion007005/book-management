package sion.mvc.dispatcher;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.http.HttpResponse;
import sion.http.HttpStatus;
import sion.mvc.ApplicationContext;
import sion.mvc.FreemarkerViewRenderFactory;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
import sion.mvc.render.JsonViewRender;
import sion.mvc.render.StaticResourceViewRender;
import sion.mvc.support.PropertiesLoader;

@SuppressWarnings("serial")
@Slf4j
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/"})
public class DispatcherServlet extends HttpServlet {
	private ControllerFactory controllerFactory;
	private InterceptorRegistry interceptorRegistry;
	
	@Override 
	public void init() { // 서블릿이 실행될 때 맨 처음 한번 실행되는 메서드 
		log.info("init 메소드 실행!!!!!!!!!");
		Properties properties = new PropertiesLoader().load();
		ApplicationContext.addProperties(properties);

		controllerFactory = ApplicationContext.getControllerFactory();
		interceptorRegistry = ApplicationContext.getInterceptorRegistry();
		log.info("dispatcherServlet is initialized...");
	}
	
	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO request response get~/set~ 메서드들 다 찍어서 뭐 넘어오는지 확인해 볼 것 
		log.info("req.getRequestURI: {}", request.getRequestURI());
		log.info("req.getRequestURL: {}", request.getRequestURL());
		log.info("req.getMethod: {}", request.getMethod());
		
//		ServletOutputStream out = response.getOutputStream();
//		out.write("HelloWorld".getBytes());
//		out.flush();
//		out.close();
		try {
			// 정적 리소스를 처리하는 로직
			if (isStaticResourceRequest(request)) {
				ViewRender viewRender = new StaticResourceViewRender();
				viewRender.render(request, response, null);
				
				return;
			}
			// 인터페이스의 구현체를 꽂아넣음
			Controller controller = controllerFactory.getInstance(controllerFactory.getKey(request));
			log.debug("뭐 들어와 컨트롤러??? : {}", controller);
			preCommand(controller, request, response);
			
			ModelAndView mav = controller.command(request, response);
			makeStatus(response, mav);
			
			postCommand(controller, request, response);
			
			render(request, response, mav);
		
		} catch (ForbiddenException e) {
			log.debug("response ??? : {}", response);
			response.sendRedirect("/login/form");
//			log.error(e.getMessage(), e);
//			
//			ModelAndView mav = new ModelAndView("error/forbidden");
//			mav.addObject("_error_message", e.getMessage());
//			response.setStatus(HttpStatus.FORBIDDEN.getCode());
//			log.debug("HttpStatus.FORBIDDEN.getCode() : {}", HttpStatus.FORBIDDEN.getCode());
//			log.debug("response.getStatus : {}", response.getStatus());
//			render(request, response, mav);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			
			ModelAndView mav = new ModelAndView("error/not_found");
			mav.addObject("_error_message", e.getMessage());
			response.setStatus(HttpStatus.NOT_FOUND.getCode());
			
			render(request, response, mav);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			ModelAndView mav = new ModelAndView("error/server_error");
			mav.addObject("errorMessage", e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
			
			render(request, response, mav);
		}
		
		
	}
	
	private void preCommand(Controller controller, HttpServletRequest request, HttpServletResponse response) throws DispatcherException {
		List<Interceptor> interceptors = interceptorRegistry.getInterceptors();
		if (Objects.isNull(interceptors)) {
			return;
		}
		
		for (Interceptor interceptor : interceptors) {
			// 인터셉터에 등록된 순서대로 실행된다.
			if (!interceptor.preHandle(request, response, controller)) {
				throw new DispatcherException(interceptor + ": interceptor return false");
			}
		}
	}
	
	private void makeStatus(HttpServletResponse response, ModelAndView mav) {
		if (mav.getViewName() == null) {
			return;
		}
		
		if (mav.getViewName().startsWith(HttpResponse.REDIRECT_NAME)) {
			response.setStatus(HttpStatus.MOVED_PERMANENTLY.getCode());
			return;
		} 			
		
		response.setStatus(HttpStatus.OK.getCode());
	}
	
	private void postCommand(Controller controller, HttpServletRequest request, HttpServletResponse response) {
		List<Interceptor> interceptors = interceptorRegistry.getInterceptors();
		if (Objects.isNull(interceptors)) {
			return;
		}
		
		// 인터셉터 등록된 순서와 거꾸로 실행한다.
		for (int i = interceptors.size() - 1; i >= 0; i--) {
			Interceptor interceptor = interceptors.get(i);
			interceptor.postHandle(request, response, controller);
		}
	}
	
	private void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		if (HttpResponse.JSON_VIEW_NAME.equals(mav.getViewName())) {
			ViewRender viewRender = new JsonViewRender();
			viewRender.render(request, response, mav);
			return;
		}
		
		log.debug("여기까지이이이이이 response.getStatus() : {}", response.getStatus());
		ViewRender responseProcessor = FreemarkerViewRenderFactory.getInstance(response.getStatus()); 
	   responseProcessor.render(request, response, mav);
	}
	
	private boolean isStaticResourceRequest(HttpServletRequest requeest) {
		List<String> pathList = ApplicationContext.getStaticResourcePathList();
		String requestURI = requeest.getRequestURI();
		for (String path : pathList) {
			if (requestURI.startsWith(path)) {
				return true;
			}
		}
		
		return false;
	}
}