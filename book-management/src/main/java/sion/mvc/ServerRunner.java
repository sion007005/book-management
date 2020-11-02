package sion.mvc;

import java.net.InetSocketAddress;
import java.util.Properties;

import com.sun.net.httpserver.HttpServer;

import sion.mvc.support.PropertiesLoader;

public class ServerRunner {
	//private으로 선언 - 특별한 경우가 아니라면, 외부에서 참조할 수 없다.
	private HttpServer server;
	private PropertiesLoader propertiesLoader;

	public ServerRunner() {
		propertiesLoader = new PropertiesLoader();
	}
	
	public static void main(String[] args) {
		System.out.println("HTTP Server Start!!");
      ServerRunner runner = new ServerRunner();
      runner.preServerStart();
		runner.serverStart();
		runner.postServerStart();
	}
	
	private void preServerStart() {
		//서버 시작 전 처리
		//1. 설정 정보(포트, DB, controllerFactory 구현체 정보) 가져오기
		Properties properties = propertiesLoader.load(PropertiesLoader.FILE_NAME);
		ServerContext.addProperties(properties);
	}

	private void serverStart() {
		try {
			server = HttpServer.create(new InetSocketAddress(ServerContext.getPort()), 0);
			server.createContext("/", new CustomHttpHandler());
			server.start(); // httpServer start
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void postServerStart() {
		//서버 종료 후 후처리 
		// TODO Auto-generated method stub
		
	}
}