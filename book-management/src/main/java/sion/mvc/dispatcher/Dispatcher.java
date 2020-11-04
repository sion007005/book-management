package sion.mvc.dispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method; //reflection 검색! 자바 클래스의 메타정보(=데이터에 대한 데이터)를 제공함 
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.Model;
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
	public HttpResponse dispatch(HttpExchange httpExchange) {
		try {
			HttpRequest httpRequest = requestHandle(httpExchange);
			Controller controller = controllerFactory.getInstance(controllerFactory.getKey(httpRequest));
			
			preCommand(controller, httpExchange);
			HttpResponse httpResponse = controller.command(httpRequest);
			postCommand(httpRequest, httpResponse);
			return httpResponse;
		} catch (UnAuthenticatedException e) {
			log.error(e.getMessage(), e);

			HttpResponse httpResponse = new HttpResponse(new Model(), "");
			httpResponse.setStatusCode(403);
			
			return httpResponse;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			Model model = new Model();
			model.put("errorMessage", e.getMessage());
			HttpResponse httpResponse = new HttpResponse(model, "");
			httpResponse.setStatusCode(500);
			e.printStackTrace();
			return httpResponse;
		}
	}
	
   private HttpRequest requestHandle(HttpExchange httpExchange) throws IOException {
      String url = makeUrl(httpExchange);
      String method = httpExchange.getRequestMethod();
      Map<String, Object> parameters = makeParameters(httpExchange);
      Map<String, Object> attributes = makeAttributes(httpExchange);

      //HttpRequest 생성해서 리턴
      return new HttpRequest(url, method, parameters, attributes);
  }
   
   private Map<String, Object> makeParameters(HttpExchange httpExchange) throws UnsupportedEncodingException {
 	  URI uri = httpExchange.getRequestURI(); //localhost:3333?id=1&name=AAA라면, id=1&name=AAA로 자른다.
 	  String uriString = uri.toString();
 	  String queryString = null;
 	  
 	  if (uriString.contains("?")) {
 		  queryString = uriString.substring(uriString.indexOf("?") + 1, uriString.length());
 	  } 
      System.out.println("query string : " + queryString);

      Map<String, Object> parameters = new HashMap<>();

      UriParser uriParser = new UriParser();
      uriParser.parseQuery(queryString, parameters);
      parameters.forEach((k, v)->System.out.println("key : " + k + " / value : " + v));
      return parameters;
   }

   private Map<String, Object> makeAttributes(HttpExchange httpExchange) throws IOException {
       String inputLine = "";
       String resultStr = "";

       InputStream in = httpExchange.getRequestBody();
       BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

       StringBuffer response = new StringBuffer();
       while((inputLine = br.readLine()) != null){
           response.append(inputLine);
       }
       String bodyString = response.toString();
       System.out.println("body string : " + bodyString);

       Map<String, Object> attributes = new HashMap<>();

       UriParser uriParser = new UriParser();
       uriParser.parseQuery(bodyString, attributes);
       attributes.forEach((k, v)->System.out.println("key : " + k + " / value : " + v));
       return attributes;
   }

   private String makeUrl(HttpExchange httpExchange) {
  	 	URI uri = httpExchange.getRequestURI();
  	 	return uri.getPath(); // 파라미터를 제외한 Path를 받아온다 (books/list 이런식) 
   }

	private void preCommand(Controller controller, HttpExchange httpExchange) throws DispatcherException {
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
			// sid=1&20201104112034 회원번호$년월일시분초 이런 형태로 쿠키에 set
			// 쿠키에 이 값이 있으면 로그인이 된 것이고, 없으면 로그인이 필요하지만 안 된 것으로 판단-> 예외던짐(403 forbidden 접근금지)  
			String sid = CookieUtils.getValue(httpExchange, "sid");
			//TODO  sid를 받아온 값이 있으면 통과 없으면 아래 예외 던지도록 
//			throw new UnAuthenticatedException(); //TODO 메세지 넣기 : 권한이  없습니다!
	   } 
	   
	}

	private void postCommand(HttpRequest httpRequest, HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		
	}


	public static Dispatcher getInstance() {
		return dispatcher;
	}

}
