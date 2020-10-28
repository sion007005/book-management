package sion.mvc;

import sion.bookmanagement.controller.Controller;
import sion.bookmanagement.controller.ControllerFactory;
import sion.bookmanagement.controller.FileNotFoundException;


public class Dispatcher {
	private static Dispatcher dispatcher = new Dispatcher();
	
	private ControllerFactory controllerFactory;
	
	private Dispatcher() {
		controllerFactory = new ControllerFactory();
	}

	/*
	 * 1. 요청 url에 맞는 controller를 찾아서 실행한다.
	 * 2. status code를 최종적으로 결정한다.
	 */
	public HttpResponse<?> dispatch(HttpRequest httpRequest) {
		try {
			Controller controller = controllerFactory.getInstance(ControllerFactory.getKey(httpRequest));
			return controller.command(httpRequest);
		} catch (FileNotFoundException fe) {
			HttpResponse<String> httpResponse = new HttpResponse<>(null, "");
			httpResponse.setStatusCode(404);
			return httpResponse;
		} catch (Exception e) {
			HttpResponse<String> httpResponse = new HttpResponse<>(e.getMessage(), "");
			httpResponse.setStatusCode(500);
			e.printStackTrace();
			return httpResponse;
		}
	}

	public static Dispatcher getInstance() {
		return dispatcher;
	}

}
