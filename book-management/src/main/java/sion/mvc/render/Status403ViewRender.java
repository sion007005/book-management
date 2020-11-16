//package sion.mvc.render;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import lombok.extern.slf4j.Slf4j;
//import sion.http.ServerRunnerException;
//import sion.mvc.ApplicationContext;
//import sion.mvc.FreemarkerConfigurationManager;
//import sion.mvc.ModelAndView;
//import sion.mvc.ViewRender;
//
//@Slf4j
//public class Status403ViewRender implements ViewRender {
//	Configuration cfg = FreemarkerConfigurationManager.getInstance();
//	ServletOutputStream out = null;
//	
//	@Override
//	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
//		try {
//			addHtmlContextHeader(response);
//			displayViewPage(response, mav);
//		} catch (Exception e) {
//			try {
//				writeMsgOnBrowser(response);
//			} catch (IOException e1) {
//				log.error(e.getMessage(), e1);
//				throw new ServerRunnerException(e1);
//			} 
//		}  
//	}
//
//	private void writeMsgOnBrowser(HttpServletResponse response) throws IOException {
//		OutputStream outputStream = response.getResponseBody();
//		outputStream.write("file not found".getBytes());
//		outputStream.close();
//	}
//
//	public void displayViewPage(HttpServletResponse response, ModelAndView mav) {
////		OutputStreamWriter writer = null;
//		
//		try {
////	      writer = new OutputStreamWriter(response.getResponseBody(), ApplicationContext.getCharsetType());
////	      Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
////	      template.process(mav.getModel(), writer); //모델만 넘겨준다.    
//			out = response.getOutputStream(); 
//			Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
//	      template.process(mav.getModel(), out); //모델만 넘겨준다. 
//      } catch (Exception e){
//      	log.error(e.getMessage(), e);
//      } finally {
//      	if (writer != null)
//				try {
//					writer.close();
//				} catch (IOException e) {
//					log.error(e.getMessage(), e);
//				}
//      }
//	}
//}
