package sion.mvc;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class Status5XXResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse<?> httpResponse) {
		try {
			httpExchange.sendResponseHeaders(500, 0); //상태코드, 바디사이즈
			OutputStream outputStream = httpExchange.getResponseBody();
			outputStream.write("unexpected error...".getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
