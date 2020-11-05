package sion.mvc;

import sion.mvc.render.Status2XXResponseProcessor;
import sion.mvc.render.Status3XXResponseProcessor;
import sion.mvc.render.Status403ResponseProcessor;
import sion.mvc.render.Status404ResponseProcessor;
import sion.mvc.render.Status5XXResponseProcessor;

public class ResponseProcessorFactory {

	/*
	 * TODO if/else 없애기
	 */
	public static ResponseProcessor getInstance(int statusCode) {
		if (statusCode >= 200 && statusCode < 300) {
			return new Status2XXResponseProcessor();
		} else if (statusCode >= 300 && statusCode < 400) {
			return new Status3XXResponseProcessor();
		} else if (statusCode == 403) {
			return new Status403ResponseProcessor();
		} else if (statusCode >= 400 && statusCode < 500) {
			return new Status404ResponseProcessor();
		} else if (statusCode >= 500 && statusCode < 600) {
			return new Status5XXResponseProcessor();
		}
		
		throw new RuntimeException("StatusCode에 해당하는 response process가 없습니다."); 
	}

}
