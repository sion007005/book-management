package sion.mvc;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class Status3XXResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse<?> httpResponse) {
		//REDIRECT
   	Headers headers = httpExchange.getResponseHeaders();
   	headers.add("Location", httpResponse.getRedirectPath());
   	try {
			httpExchange.sendResponseHeaders(302, 0);
			OutputStream outputStream = httpExchange.getResponseBody();
			outputStream.write("".getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} //상태코드, 바디사이즈
   }      	
}
