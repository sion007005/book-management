package sion.mvc;

import java.util.HashMap;
import java.util.Map;

import sion.http.HttpStatus;
import sion.mvc.render.Status200ViewRender;
import sion.mvc.render.Status301ViewRender;

public class FreemarkerViewRenderFactory {
	private static final Map<Integer, ViewRender> instances = new HashMap<>();
	static {
		instances.put(HttpStatus.OK.getCode(), new Status200ViewRender());
		instances.put(HttpStatus.MOVED_PERMANENTLY.getCode(), new Status301ViewRender());
//		instances.put(HttpStatus.FORBIDDEN.getCode(), new Status403ViewRender());
//		instances.put(HttpStatus.NOT_FOUND.getCode(), new Status404ViewRender());
//		instances.put(HttpStatus.INTERNAL_SERVER_ERROR.getCode(), new Status500ViewRender());
	}
	
	/*
	 * if/else 없애기
	 */
	public static ViewRender getInstance(int httpStatusCode) {
		ViewRender viewRender = instances.get(httpStatusCode);
		if (viewRender == null) {
			throw new RuntimeException("StatusCode에 해당하는 response process가 없습니다."); 
		}
		
		return viewRender;
	}

}
