package sion.mvc;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import lombok.extern.slf4j.Slf4j;
import sion.http.Executor;

@Slf4j
public class CustomHttpHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        //아래 로직을 쓰레드로 실행하도록 변경
        try {
      	  Runnable runnable = new Executor(httpExchange);
      	  Thread thread = new Thread(runnable);
      	  thread.start();
        } catch (Exception e){
      	  log.error(e.getMessage(), e);
        }
    }
}
