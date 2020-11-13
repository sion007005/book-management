package sion.mvc.render;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;

@Slf4j
public class JsonViewRender implements ViewRender {

	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		OutputStreamWriter writer = null;
		ObjectMapper mapper = new ObjectMapper(); //자바 객체를 json스트링으로 바꾸도록 도와줌 

		try {
			addJsonContextHeader(httpResponse.getHeaders()); //내려가는 컨텐츠가 json이라고 브라우저에게 알려줌
			httpResponse.sendResponseHeaders(httpResponse.getStatusCode(), 0);
			//model에 있는 데이터를 json value로 만들어준다.
			String jsonValue = mapper.writeValueAsString(httpResponse.getModelAndView().getModel());
			log.info("jsonvalue: {}", jsonValue);
			writer = new OutputStreamWriter(httpResponse.getResponseBody(), "UTF-8");
			writer.write(jsonValue); //json으로 브라우저에 내려줌
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new ViewRenderException(e);
				}
			}
		}
	}

}
