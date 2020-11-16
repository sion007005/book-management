package sion.mvc.render;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;

@Slf4j
public class JsonViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		ServletOutputStream out = null;
		ObjectMapper mapper = new ObjectMapper(); //자바 객체를 json스트링으로 바꾸도록 도와줌 

		try {
			addJsonContextHeader(response); //내려가는 컨텐츠가 json이라고 브라우저에게 알려줌
//			response.sendResponseHeaders(response.getStatusCode(), 0);
			//model에 있는 데이터를 json value로 만들어준다.
			String jsonValue = mapper.writeValueAsString(mav.getModel());
			log.info("jsonvalue: {}", jsonValue);
			
			out = response.getOutputStream();
			out.write(jsonValue.getBytes());
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					throw new ViewRenderException(e);
				}
			}
		}
	}

}
