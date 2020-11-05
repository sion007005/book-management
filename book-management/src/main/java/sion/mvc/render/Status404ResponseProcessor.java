package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;
import sion.mvc.ServerRunnerException;

@Slf4j
public class Status404ResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
		try {
			httpExchange.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			OutputStream outputStream = httpExchange.getResponseBody();
			
			//1. 이거 추가한다음에 
//			Headers h = httpExchange.getResponseHeaders();
//			h.add("Content-Type", "text/html;charset=UTF-8"); //"application/json;charset=UTF-8"
//			h.add("Access-Control-Allow-Origin", "*");
			//2. "" 안에 html 태그 넣어도 렌더링 할 수도 있음 
			outputStream.write("file not found".getBytes());
			outputStream.close();
		} catch (IOException e) {
			//TODO 로그찍고 exception throw 하도록
			// server Runner exception 으로! 
			log.error(e.getMessage(), e);
			throw new ServerRunnerException(e);
		} 
	}

}
