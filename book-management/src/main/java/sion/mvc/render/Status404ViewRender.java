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
import sion.mvc.ApplicationContext;
import sion.mvc.ServerRunnerException;
import sion.mvc.ViewRender;

@Slf4j
public class Status404ViewRender implements ViewRender {
	Configuration cfg = FreemarkerConfigurationManager.getInstance();
	
	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		try {
			httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
			addHtmlContextHeader(httpResponse.getHeaders());
			displayViewPage(httpResponse, mav);
		} catch (Exception e) {
			try {
				httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
				writeMsgOnBrowser(httpResponse);
			} catch (IOException e1) {
				log.error(e.getMessage(), e1);
				throw new ServerRunnerException(e1);
			} 
		}
	}

	private void writeMsgOnBrowser(HttpResponse httpResponse) throws IOException {
		OutputStream outputStream = httpResponse.getResponseBody();
		outputStream.write("file not found".getBytes());
		outputStream.close();
	}

	public void displayViewPage(HttpResponse httpResponse, ModelAndView mav) {
		OutputStreamWriter writer = null;
		
		try { //TODO template 값이 제대로 안 넘어오면?
	      writer = new OutputStreamWriter(httpResponse.getResponseBody(), ApplicationContext.getCharsetType());
	      Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
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
