package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;

@Slf4j
public class Status301ViewRender implements ViewRender {
	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		//REDIRECT
   	Headers headers = httpResponse.getHeaders();
   	headers.add("Location", httpResponse.getRedirectPath());
   	
   	try {
   		httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0); //상태코드, 바디사이즈
   		writeMsgOnBrowser(httpResponse);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
   }

	private void writeMsgOnBrowser(HttpResponse httpResponse) throws IOException {
		OutputStream outputStream = httpResponse.getResponseBody();
		outputStream.write("".getBytes());
		outputStream.close();
	}      	
}
