package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;

public class Status404ResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
		try {
			httpExchange.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			OutputStream outputStream = httpExchange.getResponseBody();
			outputStream.write("file not found".getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
