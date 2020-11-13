package sion.mvc.render;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;
import sion.mvc.HttpStatus;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
import sion.mvc.dispatcher.FileNotFoundException;
@Slf4j
public class StaticResourceViewRender implements ViewRender {

	@Override
	public void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav) {
		InputStreamReader reader = null;
		OutputStreamWriter writer = null;

		try {
			//TODO css context로 변경해야 함
			addStyleSheetContextHeader(httpResponse.getHeaders());
			httpResponse.sendResponseHeaders(HttpStatus.OK.getCode(), 0);

			InputStream is = getClass().getResourceAsStream(httpRequest.getUriPath()); // class path에서 찾는다. 
			if (is == null) {
				throw new FileNotFoundException("파일을 찾을 수 없습니다. " + httpRequest.getUriPath());
			}

			reader = new InputStreamReader(is, "UTF-8");
			writer = new OutputStreamWriter(httpResponse.getResponseBody(), "UTF-8");

			char[] buffer = new char[512]; //512바이트를 한번에 읽어와서 쓰도록 함(원래는 1바이트씩)
			int readCount = 0;
			while ((readCount = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			try {
				if (Objects.nonNull(reader)) {
					reader.close();
				}
				
				if (Objects.nonNull(writer)) {
					writer.close();
				}
			} catch (IOException e) {
				throw new ViewRenderException(e);
			}
		}
	}
}
