package sion.mvc;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class HttpServerModule {
    public HttpServerModule(){
        HttpServer server;
        Integer listenPort = 3333;

        try {
            server = HttpServer.create(new InetSocketAddress(listenPort), 0);
            server.createContext("/", new CustomHttpHandler());
            server.start(); //httpServer start
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}