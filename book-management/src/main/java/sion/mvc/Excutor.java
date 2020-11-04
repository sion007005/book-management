package sion.mvc;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import sion.mvc.dispatcher.Dispatcher;

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

         Dispatcher dispatcher = Dispatcher.getInstance();
         HttpResponse httpResponse = dispatcher.dispatch(httpExchange);
         
         responseHandle(httpExchange, httpResponse);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

  private void responseHandle(HttpExchange httpExchange, HttpResponse httpResponse) throws IOException {
     ResponseProcessor responseProcessor = ResponseProcessorFactory.getInstance(httpResponse.getStatusCode()); 
	  responseProcessor.proccess(httpExchange, httpResponse);
  }

  
}