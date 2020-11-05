package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sion.mvc.FreemarkerConfigurationFactory;
import sion.mvc.HttpResponse;

@Slf4j
public class HtmlRender {

	public void render(HttpExchange httpExchange, HttpResponse httpResponse) {
		Configuration cfg = FreemarkerConfigurationFactory.getInstance();
		OutputStreamWriter writer = null;

		try {

			// HttpResponse 받아서 클라이언트로 넘겨주기
			Headers h = httpExchange.getResponseHeaders();
			h.add("Content-Type", "text/html;charset=UTF-8"); // "application/json;charset=UTF-8"
			h.add("Access-Control-Allow-Origin", "*");

			httpExchange.sendResponseHeaders(200, 0);
			writer = new OutputStreamWriter(httpExchange.getResponseBody(), "UTF-8");

			Template template = cfg.getTemplate(httpResponse.getViewName() + ".ftl");
			template.process(httpResponse.getModel(), writer); // 모델만 넘겨준다.
		} catch (Exception e) {
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
