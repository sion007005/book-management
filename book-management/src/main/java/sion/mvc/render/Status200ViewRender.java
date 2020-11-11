package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStreamWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.mvc.FreemarkerConfigurationManager;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.ServerContext;
import sion.mvc.ViewRender;
@Slf4j
public class Status200ViewRender implements ViewRender {
	
	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
	 	//FORWARDING
		Configuration cfg = FreemarkerConfigurationManager.getInstance();
		OutputStreamWriter writer = null;

		try {
			// HttpResponse 받아서 클라이언트로 넘겨주기
			addHtmlContextHeader(httpResponse.getHeaders());

			httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0);
			writer = new OutputStreamWriter(httpResponse.getResponseBody(), ServerContext.getCharsetType());

			Template template = cfg.getTemplate(mav.getViewName() + ServerContext.getViewFileType());
			template.process(mav.getModel(), writer); // 모델만 넘겨준다.
		} catch (Exception e) {
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
