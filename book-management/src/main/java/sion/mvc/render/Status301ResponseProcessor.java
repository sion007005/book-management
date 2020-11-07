package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;

@Slf4j
public class Status301ResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
		//REDIRECT
   	Headers headers = httpExchange.getResponseHeaders();
   	headers.add("Location", httpResponse.getRedirectPath());
   	try {
			httpExchange.sendResponseHeaders(302, 0); //상태코드, 바디사이즈
			OutputStream outputStream = httpExchange.getResponseBody();
			outputStream.write("".getBytes());
			outputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
   }      	
}
