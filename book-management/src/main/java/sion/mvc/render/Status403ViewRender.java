package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.mvc.FreemarkerConfigurationManager;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.ServerRunnerException;
import sion.mvc.ViewRender;

@Slf4j
public class Status403ViewRender implements ViewRender {
	Configuration cfg = FreemarkerConfigurationManager.getInstance();

	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		//TODO 최대한 단순하게 코드 정리 
		try {
			Template template = cfg.getTemplate(mav.getViewName() + ".ftl");

			if (template == null) { //null이 넘어오나? 불필요한코드가 아닌가?
				throw new Exception();
			} 
		} catch (Exception e) {
		//프리마커 파일로 말고 원래코드대로 outputstream 해주는 코드
			try {
				httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
				OutputStream outputStream = httpResponse.getResponseBody();
				
				outputStream.write("file not found".getBytes());
				outputStream.close();
			} catch (IOException e1) {
				log.error(e.getMessage(), e1);
				throw new ServerRunnerException(e1);
			} 
		} 
		
		try {
			httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			//브라우저에게 html로 내려주겠다고 알려줌
			//TODO 공통으로 분리하도록.... 
			addHtmlContextHeader(httpResponse.getHeaders());
			
			//OutputStream outputStream = httpExchange.getResponseBody();
			//권한 없음, 접근 금지!
			render(httpResponse, mav);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new ServerRunnerException(e);
		} 
	}

	public void render(HttpResponse httpResponse, ModelAndView mav) {
		OutputStreamWriter writer = null;
		
		try {
	      writer = new OutputStreamWriter(httpResponse.getResponseBody(), "UTF-8");
	      Template template = cfg.getTemplate(mav.getViewName() + ".ftl");
	      template.process(mav.getModel(), writer); //모델만 넘겨준다.    
      } catch (Exception e){
      	log.error(e.getMessage(), e);
      } finally {
      	if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
      }
	}
}
