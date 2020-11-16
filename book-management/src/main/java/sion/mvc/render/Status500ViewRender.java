//package sion.mvc.render;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import lombok.extern.slf4j.Slf4j;
//import sion.http.HttpRequest;
//import sion.http.HttpResponse;
//import sion.http.ServerRunnerException;
//import sion.mvc.ApplicationContext;
//import sion.mvc.FreemarkerConfigurationManager;
//import sion.mvc.ModelAndView;
//import sion.mvc.ViewRender;
//
//@Slf4j
//public class Status500ViewRender implements ViewRender {
//	Configuration cfg = FreemarkerConfigurationManager.getInstance();
//	
//	@Override
//	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
//		try {
//			httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
//			addHtmlContextHeader(httpResponse.getHeaders());
//			displayViewPage(httpResponse, mav);
//		} catch (Exception e) {
//			try {
//				httpResponse.sendResponseHeaders(500, 0); //상태코드, 바디사이즈
//				writeMsgOnBrowser(httpResponse);
//			} catch (IOException e1) {
//				log.error(e.getMessage(), e1);
//				throw new ServerRunnerException(e1);
//			}
//		}
//	}
//
//	private void writeMsgOnBrowser(HttpResponse httpResponse) throws IOException {
//		OutputStream outputStream = httpResponse.getResponseBody();
//		outputStream.write("unexpected error...".getBytes());
//		outputStream.close();
//	}
//
//	public void displayViewPage(HttpResponse httpResponse, ModelAndView mav) {
//		OutputStreamWriter writer = null;
//		
//		try {
//	      writer = new OutputStreamWriter(httpResponse.getResponseBody(), ApplicationContext.getCharsetType());
//	      Template template = cfg.getTemplate(mav.getViewName() + ApplicationContext.getViewFileType());
//	      template.process(mav.getModel(), writer); //모델만 넘겨준다.    
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
