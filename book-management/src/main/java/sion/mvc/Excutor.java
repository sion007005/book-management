package sion.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public class Excutor implements Runnable {
	
	private final HttpExchange httpExchange;

	public Excutor(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public void run() {
		try {
         System.out.println("URI : " + httpExchange.getRequestURI() + " Method : " + httpExchange.getRequestMethod());
         
         if(httpExchange.getRequestURI().getPath().startsWith("/favicon.ico")) {
         	return;
         }

         HttpRequest httpRequest = requestHandle(httpExchange);
         //dispatcher에 dispatch 실행하기
         Dispatcher dispatcher = Dispatcher.getInstance();
         HttpResponse<?> httpResponse = dispatcher.dispatch(httpRequest);
         
         responseHandle(httpExchange, httpResponse);
		} catch (Exception e){
			e.printStackTrace();
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

  private void responseHandle(HttpExchange httpExchange, HttpResponse<?> httpResponse) throws IOException {
     ResponseProcessor responseProcessor = ResponseProcessorFactory.getInstance(httpResponse.getStatusCode()); 
	  responseProcessor.proccess(httpExchange, httpResponse);
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
     System.out.println("파라미터입니다아아아");
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
}