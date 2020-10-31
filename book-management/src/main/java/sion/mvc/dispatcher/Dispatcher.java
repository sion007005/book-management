package sion.mvc.dispatcher;

import java.lang.reflect.Method; //reflection 검색! 자바 클래스의 메타정보(=데이터에 대한 데이터)를 제공함 

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
import sion.mvc.ServerContext;

public class Dispatcher {
	private static Dispatcher dispatcher = new Dispatcher();
	
	private ControllerFactory controllerFactory;
	
	private Dispatcher() {
		controllerFactory = ServerContext.getControllerFactory();
	}

	/*
	 * 1. 요청 url에 맞는 controller를 찾아서 실행한다.
	 * 2. status code를 최종적으로 결정한다.
	 */
	public HttpResponse dispatch(HttpRequest httpRequest) {
		try {
			Controller controller = controllerFactory.getInstance(controllerFactory.getKey(httpRequest));
			preCommand(controller, httpRequest);
			HttpResponse httpResponse = controller.command(httpRequest);
			postCommand(httpRequest, httpResponse);
			return httpResponse;
		} catch (FileNotFoundException fe) {
			HttpResponse httpResponse = new HttpResponse(new Model(), "");
			httpResponse.setStatusCode(404);
			return httpResponse;
		} catch (Exception e) {
			Model model = new Model();
			model.put("errorMessage", e.getMessage());
			HttpResponse httpResponse = new HttpResponse(model, "");
			httpResponse.setStatusCode(500);
			e.printStackTrace();
			return httpResponse;
		}
	}

	private void preCommand(Controller controller, HttpRequest httpRequest) throws DispatcherException {
		//1. 로그인 체크 -> 필요한가(=@Login이 있는가)? 
		// 1-1) 필요하다면, 로그인이 되었는가? 확인 
		//  되었으면, controller.command 실행 / ㅏ안되었으면 로그인 페이지로 redirection
		Login login = null;
		
		try {
			//getClass()로 controller 클래스의 메타정보를 가져와서 -> 그 중에서도 method 이름이 command이고 파라미터가 httpRequest인 것을 가져와라 
			Method method = controller.getClass().getMethod("command", HttpRequest.class); 
			//그 메서드에서 선언된 어노테이션 정보를 가져와라
			 login = method.getDeclaredAnnotation(Login.class);
		}  catch (Exception e) { 
	   	throw new DispatcherException(e);
	   }
	   
		if (login != null) {
	   	// 1-1) 로그인 되었는지 체크
	   } 
	   
	}

	private void postCommand(HttpRequest httpRequest, HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		
	}


	public static Dispatcher getInstance() {
		return dispatcher;
	}

}
