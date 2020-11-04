package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;

public class Status403ResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
		try {
			httpExchange.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			OutputStream outputStream = httpExchange.getResponseBody();
			//권한 없음, 접근 금지!
			outputStream.write("forbidden".getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
