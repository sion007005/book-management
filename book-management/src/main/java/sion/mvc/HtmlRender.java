package sion.mvc;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.utility.TemplateModelUtils;

public class HtmlRender {

	public void render(HttpExchange httpExchange, HttpResponse<?> httpResponse) {
		Configuration cfg = FreemarkerConfigurationFactory.getInstance(); 
		OutputStreamWriter writer = null;
		
		try {
			
	      //HttpResponse 받아서 클라이언트로 넘겨주기
	      Headers h = httpExchange.getResponseHeaders();
	      h.add("Content-Type", "text/html;charset=UTF-8"); //"application/json;charset=UTF-8"
	      h.add("Access-Control-Allow-Origin", "*");

	      httpExchange.sendResponseHeaders(200, 0);
	      writer = new OutputStreamWriter(httpExchange.getResponseBody(), "UTF-8");
	      
//	      TemplateModel templateModel = new SimpleObjectWrapper().wrap(httpResponse.getBody());
	      cfg.setObjectWrapper(new DefaultObjectWrapper());
	      cfg.setObjectWrapper(new BeansWrapper());
	      
	      Template template = cfg.getTemplate(httpResponse.getViewName() + ".ftl");
	      
	      
	      template.process(httpResponse, writer);    
      }catch(Exception e){
          e.printStackTrace();
      }finally{
      	if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

          httpExchange.close();
      }
	}
}
