package sion.bookmanagement.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.Commander;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;
import sion.mvc.dispatcher.GetMapper;
import sion.mvc.dispatcher.PostMapper;

@Slf4j
public class AnnotationBookManagementControllerFactory implements ControllerFactory {
	Map<String, Commander> controllers = new HashMap<>();
	
	public AnnotationBookManagementControllerFactory() {
		initialize();
	}

	private void initialize() {
		
		List<Commander> commanders = new ArrayList<>(); //TODO @Controller가 있는 Command클래스를 가져와야 한다.
		
		// 2. command 메소드에 request mapping annotaion을 찾아서 controllers map(13번 줄)을 생성한다.
		for (Commander commander : commanders) {
			String getKey = getGetMapperKey(commander);
			if (Objects.nonNull(getKey)) {
				controllers.put(getKey, commander);
			}
			
			String postKey = getPostMapperKey(commander);
			if (Objects.nonNull(postKey)) {
				controllers.put(postKey, commander);
			}
		}
	}
	
	private String getGetMapperKey(Commander controller) {
		try {
			Method command = getCommandMethod(controller);
			GetMapper get = command.getDeclaredAnnotation(GetMapper.class);

			if (get == null) {
				return null;
			}

			return get.value() +"&" + get.method();
			
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private String getPostMapperKey(Commander controller) {
		try {
			Method command = getCommandMethod(controller);
			PostMapper post = command.getDeclaredAnnotation(PostMapper.class);
		
			if (post == null) {
				return null;
			}
			
			return post.value() +"&" + post.method(); // "/member/list&POST"
		
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private Method getCommandMethod(Commander controller) throws NoSuchMethodException {
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
	public Commander getInstance(String key) {
		Commander controller = controllers.get(key);
		
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
