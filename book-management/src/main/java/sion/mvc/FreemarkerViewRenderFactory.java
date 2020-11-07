package sion.mvc;

import java.util.HashMap;
import java.util.Map;

import sion.mvc.render.Status200ViewRender;
import sion.mvc.render.Status301ViewRender;
import sion.mvc.render.Status403ViewRender;
import sion.mvc.render.Status404ViewRender;
import sion.mvc.render.Status500ViewRender;

public class FreemarkerViewRenderFactory {
	private static final Map<HttpStatus, ViewRender> instances = new HashMap<>();
	static {
		instances.put(HttpStatus.OK, new Status200ViewRender());
		instances.put(HttpStatus.MOVED_PERMANENTLY, new Status301ViewRender());
		instances.put(HttpStatus.FORBIDDEN, new Status403ViewRender());
		instances.put(HttpStatus.NOT_FOUND, new Status404ViewRender());
		instances.put(HttpStatus.INTERNAL_SERVER_ERROR, new Status500ViewRender());
	}
	
	/*
	 * if/else 없애기
	 */
	public static ViewRender getInstance(HttpStatus httpStatus) {
		ViewRender responseProcessor = instances.get(httpStatus);
		if (responseProcessor == null) {
			throw new RuntimeException("StatusCode에 해당하는 response process가 없습니다."); 
		}
		
		return responseProcessor;
	}

}
