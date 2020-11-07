package sion.mvc;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.Dispatcher;

@Slf4j
public class Excutor implements Runnable {
	private final HttpExchange httpExchange;

	public Excutor(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public void run() {
		try {
			log.info("URI : " + httpExchange.getRequestURI() + " Method : " + httpExchange.getRequestMethod());
         
         if (httpExchange.getRequestURI().getPath().startsWith("/favicon.ico")) {
         	return;
         }

         Dispatcher dispatcher = Dispatcher.getInstance();
         HttpResponse httpResponse = dispatcher.dispatch(httpExchange);
         
         responseHandle(httpExchange, httpResponse);
		} catch (Exception e){
			log.error(e.getMessage(), e);
		}
	}

  private void responseHandle(HttpExchange httpExchange, HttpResponse httpResponse) throws IOException {
     ResponseProcessor responseProcessor = ResponseProcessorFactory.getInstance(httpResponse.getHttpStatus()); 
     responseProcessor.proccess(httpExchange, httpResponse);
  }

  
}