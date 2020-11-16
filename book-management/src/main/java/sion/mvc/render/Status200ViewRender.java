package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.mvc.ApplicationContext;
import sion.mvc.FreemarkerConfigurationManager;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
@Slf4j
public class Status200ViewRender implements ViewRender {
	
	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	 	//FORWARDING
		Configuration cfg = FreemarkerConfigurationManager.getInstance();
		OutputStreamWriter writer = null;

		try {
			// HttpResponse 받아서 클라이언트로 넘겨주기
			addHtmlContextHeader(response);
			// response.getOutputStream() 네트워크로 내려줄 길을 찾아 옴 
			writer = new OutputStreamWriter(response.getOutputStream(), ApplicationContext.getCharsetType());

			Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
			template.process(mav.getModel(), writer); // 모델만 넘겨준다.
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}	
		}	
	}
}
