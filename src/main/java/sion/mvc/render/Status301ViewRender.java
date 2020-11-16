package sion.mvc.render;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;

@Slf4j
public class Status301ViewRender implements ViewRender {
	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		//REDIRECT
//   	Headers headers = response.getHeaders();
//   	headers.add("Location", response.getRedirectPath());
   	response.addHeader("Location", mav.getViewName());
   	
   	try {
//   		response.sendResponseHeaders(response.getStatus(), 0); //상태코드, 바디사이즈
   		writeMsgOnBrowser(response);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
   }

	private void writeMsgOnBrowser(HttpServletResponse response) throws IOException {
//		OutputStream outputStream = httpResponse.getResponseBody();
//		outputStream.write("".getBytes());
//		outputStream.close();
		
		ServletOutputStream out =  response.getOutputStream();
		out.write("".getBytes());
		out.close();
	}      	
}
