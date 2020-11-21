package sion.bookmanagement.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.Application;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;
import sion.mvc.dispatcher.GetMapping;
import sion.mvc.dispatcher.PostMapping;

@Slf4j
public class AnnotationBookManagementControllerFactory implements ControllerFactory {
	Map<String, ControllerAware> controllers = new HashMap<>();
	
	public AnnotationBookManagementControllerFactory() {
		initialize();
	}

	private void initialize() {
		// 1.  Controller클래스를 모두 가져온다.
		String basePackageName = new Application().getClass().getPackage().getName();
		log.info("basePackageName : {}", basePackageName);
		Reflections reflector = new Reflections(basePackageName);
//		Set<Class<? extends ControllerAware>> controllerSet = reflector.getSubTypesOf(ControllerAware.class);
		
//		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Controller.class);
		
		// 2. controllers map에  path, controller를 key value로 추가한다.
		//TODO controllerSet으로 가져오도록 구현 (Controller annotation 지우기)
//		
//		for (Class<?> clazz : list) {
//			try {
//				ControllerAware controller = (ControllerAware) clazz.getDeclaredConstructor().newInstance();
//				String getKey = getGetMappingKey(controller);
//				if (Objects.nonNull(getKey)) {
//					controllers.put(getKey, controller);
//				}
//				
//				String postKey = getPostMappingKey(controller);
//				if (Objects.nonNull(postKey)) {
//					controllers.put(postKey, controller);
//				}
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			} 
//		}

	}
	
	private String getGetMappingKey(ControllerAware controller) {
		try {
			Method command = getCommandMethod(controller);
			GetMapping get = command.getDeclaredAnnotation(GetMapping.class);

			if (get == null) {
				return null;
			}

			return get.value() +"&" + get.method();
			
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private String getPostMappingKey(ControllerAware controller) {
		try {
			Method command = getCommandMethod(controller);
			PostMapping post = command.getDeclaredAnnotation(PostMapping.class);
		
			if (post == null) {
				return null;
			}
			
			return post.value() +"&" + post.method(); // "/member/list&POST"
		
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private Method getCommandMethod(ControllerAware controller) throws NoSuchMethodException {
		Method command = controller.getClass().getMethod("command", HttpServletRequest.class, HttpServletResponse.class);
		return command;
	}
	
	
	
//	private String getPath(Commander controller) {
//		PostMapper post = null;
//		GetMapper get = null;
//		String path = "";
//		String method = "";
//		
//		try {
//			Method command = controller.getClass().getMethod("command", HttpServletRequest.class, HttpServletResponse.class);
//			post = command.getDeclaredAnnotation(PostMapper.class);
//			get = command.getDeclaredAnnotation(GetMapper.class);
//		} catch (Exception e) {
//			// TODO exception 던지기 
//			log.error("", e);
//		}
//		
//		if (post != null) {
//			path = post.value();
//			method = post.method();
//		} else if (get != null) {
//			path = get.value();
//			method = "GET";
//		}
//		return path +"&" + method; // "/member/list&POST"
//	}
		
	@Override
	public ControllerAware getInstance(String key) {
		ControllerAware controller = controllers.get(key);
		
		if (controller == null) {
			throw new FileNotFoundException("해당하는 controller가 없습니다.");
		}
		
		return controllers.get(key);
	}
	
	@Override 
	public String getKey(HttpServletRequest request) {
		return request.getRequestURI() + "&" + request.getMethod();
	}
}
