package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.http.HttpStatus;
import sion.http.ServerRunnerException;
import sion.mvc.ApplicationContext;
import sion.mvc.FreemarkerConfigurationManager;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;

@Slf4j
public class Status403ViewRender implements ViewRender {
	Configuration cfg = FreemarkerConfigurationManager.getInstance();
	ServletOutputStream out = null;
	OutputStreamWriter writer = null;
	
	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		log.info("들어와요??");
		try {
			response.setStatus(HttpStatus.MOVED_PERMANENTLY.getCode());
			response.sendRedirect("/login/form");
		} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServerRunnerException(e);
		}  
	}

	private void writeMsgOnBrowser(HttpServletResponse response) throws IOException {
		out = response.getOutputStream();
		out.write("file not found".getBytes());
		out.close();
	}

	public void displayViewPage(HttpServletResponse response, ModelAndView mav) {
		try {
			writer = new OutputStreamWriter(response.getOutputStream(), ApplicationContext.getCharsetType());
			Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
	      template.process(mav.getModel(), writer); //모델만 넘겨준다. 
      } catch (Exception e){
      	log.error(e.getMessage(), e);
      } finally {
      	if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
      }
	}
}
