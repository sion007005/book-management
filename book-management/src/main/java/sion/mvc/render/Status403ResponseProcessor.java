package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.mvc.FreemarkerConfigurationManager;
import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;
import sion.mvc.ServerRunnerException;

@Slf4j
public class Status403ResponseProcessor implements ResponseProcessor {
	Configuration cfg = FreemarkerConfigurationManager.getInstance();

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
		
		try {
			Template template = cfg.getTemplate(httpResponse.getViewName() + ".ftl");

			if (template == null) {
				throw new Exception();
			} 
		} catch (Exception e) {
		//프리마커 파일로 말고 원래코드대로 outputstream 해주는 코드
			try {
				httpExchange.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
				OutputStream outputStream = httpExchange.getResponseBody();
				
				outputStream.write("file not found".getBytes());
				outputStream.close();
			} catch (IOException e1) {
				log.error(e.getMessage(), e1);
				throw new ServerRunnerException(e1);
			} 
			
		} 
		
		try {
			httpExchange.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			//브라우저에게 html로 내려주겠다고 알려줌
			Headers h = httpExchange.getResponseHeaders();
			h.add("Content-Type", "text/html;charset=UTF-8"); //"application/json;charset=UTF-8"
			h.add("Access-Control-Allow-Origin", "*");
			
			//OutputStream outputStream = httpExchange.getResponseBody();
			//권한 없음, 접근 금지!
			render(httpExchange, httpResponse);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new ServerRunnerException(e);
		} 
	}

	public void render(HttpExchange httpExchange, HttpResponse httpResponse) {
		 
		OutputStreamWriter writer = null;
		
		try {
	      writer = new OutputStreamWriter(httpExchange.getResponseBody(), "UTF-8");
	      Template template = cfg.getTemplate(httpResponse.getViewName() + ".ftl");
	      template.process(httpResponse.getModel(), writer); //모델만 넘겨준다.    
      } catch (Exception e){
      	log.error(e.getMessage(), e);
      } finally {
      	if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}

          httpExchange.close();
      }
	}
}
