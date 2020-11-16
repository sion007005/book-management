package sion.http;

import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.Dispatcher;

@Slf4j
public class Executor implements Runnable {
	private final HttpExchange httpExchange;

	public Executor(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public void run() {
		try {
			log.info("URI : " + httpExchange.getRequestURI() + " Method : " + httpExchange.getRequestMethod());
         
         HttpRequest httpRequest = new HttpRequest(httpExchange);
			HttpResponse httpResponse = new HttpResponse(httpExchange);
         Dispatcher dispatcher = Dispatcher.getInstance();
         dispatcher.service(httpRequest, httpResponse);
         httpExchange.close();
		} catch (Exception e){
			log.error(e.getMessage(), e);
		}
	}
}