package sion.mvc.dispatcher;

import java.lang.reflect.Method; //reflection 검색! 자바 클래스의 메타정보(=데이터에 대한 데이터)를 제공함 

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.util.StringUtils;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.HttpStatus;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
import sion.mvc.FreemarkerViewRenderFactory;
import sion.mvc.ServerContext;
import sion.mvc.support.CookieUtils;

@Slf4j
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
	public void dispatch(HttpRequest httpRequest, HttpResponse httpResponse) {
		try {
			Controller controller = controllerFactory.getInstance(controllerFactory.getKey(httpRequest));
			
			preCommand(controller, httpRequest, httpResponse);
			ModelAndView mav = controller.command(httpRequest, httpResponse);
			postCommand(httpRequest, httpResponse);
			
			render(httpRequest, httpResponse, mav);
		} catch (ForbiddenException e) {
			log.error(e.getMessage(), e);
			
			ModelAndView mav = new ModelAndView("error/forbidden");
			mav.put("_error_message", e.getMessage());
			httpResponse.setHttpStatus(HttpStatus.FORBIDDEN);
			render(httpRequest, httpResponse, mav);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			
			ModelAndView mav = new ModelAndView("error/not_found");
			mav.put("_error_message", e.getMessage());
			httpResponse.setHttpStatus(HttpStatus.NOT_FOUND);
			render(httpRequest, httpResponse, mav);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			ModelAndView mav = new ModelAndView("error/server_error");
			mav.put("errorMessage", e.getMessage());
			httpResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			render(httpRequest, httpResponse, mav);
		}
	}
	
	private void preCommand(Controller controller, HttpRequest httpRequest, HttpResponse httpResponse) throws DispatcherException {
		//로그인 체크 -> 필요한가(=@Login이 있는가)? 
		// 1) 필요하다면, 
		//   1-1) 로그인이 되었는가? 확인 후, controller.command 실행 
		//   1-1) 로그인이 안 된 상태면, 로그인 페이지로 redirection
		Login login = null;
		
		try {
			//getClass()로 controller 클래스의 메타정보를 가져와서 -> 그 중에서도 method 이름이 command이고 파라미터가 httpRequest인 것을 가져와라 
			Method method = controller.getClass().getMethod("command", HttpRequest.class); 
			//그 메서드에서 선언된 어노테이션 정보를 가져와라
			 login = method.getDeclaredAnnotation(Login.class);
		}  catch (Exception e) { 
	   	throw new DispatcherException(e);
	   }
	   
		// 1) 로그인이 필요한 작업이라면 
		if (login != null) {
	   	// 1-1) 로그인 되었는지 체크
			// sid=1&20201104112034 회원번호$년월일시분초 이런 형태로 쿠키에 set
			// 쿠키에 sid 값이 있으면 로그인이 된 것이고, 없으면 로그인이 필요하지만 안 된 것으로 판단-> 예외던짐(403 forbidden 접근금지)  
			String sid = CookieUtils.getValue(httpRequest.getHeaders(), "sid");
			
			//TODO sid에 넣어준 아이디값이 db에 정말 있는 회원인지 체크해줘야 함  
			
			//sid 값이 없으면 예외를 던진다. (위에 dispatch 메서드가 예외를 받아서, 에러 페이지 띄운다) 
			if (StringUtils.isEmpty(sid)) {
				throw new ForbiddenException("권한이 없는 페이지입니다.");
			} 
	   } 
	}

	private void postCommand(HttpRequest httpRequest, HttpResponse httpResponse) {
	}

	public static Dispatcher getInstance() {
		return dispatcher;
	}
	
	private void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
	     ViewRender responseProcessor = FreemarkerViewRenderFactory.getInstance(httpResponse.getHttpStatus()); 
	     responseProcessor.render(httpRequest, httpResponse, mav);
	  }

}
