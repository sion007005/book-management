package sion.mvc;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CustomHttpHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        //아래 로직을 쓰레드로 실행하도록 변경
        try {
            Runnable runnable = new Excutor(httpExchange);
            Thread thread = new Thread(runnable);
            thread.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
