package sion.mvc;

import java.util.HashMap;
import java.util.Map;

import sion.mvc.render.Status200ResponseProcessor;
import sion.mvc.render.Status301ResponseProcessor;
import sion.mvc.render.Status403ResponseProcessor;
import sion.mvc.render.Status404ResponseProcessor;
import sion.mvc.render.Status500ResponseProcessor;

public class ResponseProcessorFactory {
	private static final Map<HttpStatus, ResponseProcessor> instances = new HashMap<>();
	static {
		instances.put(HttpStatus.OK, new Status200ResponseProcessor());
		instances.put(HttpStatus.MOVED_PERMANENTLY, new Status301ResponseProcessor());
		instances.put(HttpStatus.FORBIDDEN, new Status403ResponseProcessor());
		instances.put(HttpStatus.NOT_FOUND, new Status404ResponseProcessor());
		instances.put(HttpStatus.INTERNAL_SERVER_ERROR, new Status500ResponseProcessor());
	}
	
	/*
	 * if/else 없애기
	 */
	public static ResponseProcessor getInstance(HttpStatus httpStatus) {
		ResponseProcessor responseProcessor = instances.get(httpStatus);
		if (responseProcessor == null) {
			throw new RuntimeException("StatusCode에 해당하는 response process가 없습니다."); 
		}
		
		return responseProcessor;
	}

}
