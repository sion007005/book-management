package sion.mvc.render;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;
import sion.mvc.ViewRender;
@Slf4j
public class StaticResourceViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
//		InputStreamReader reader = null;
		BufferedInputStream in = null;
		ServletOutputStream out = null;

		try {
			addStyleSheetContextHeader(response);
			
			in = new BufferedInputStream(getClass().getResourceAsStream(request.getRequestURI()));
			out = response.getOutputStream();

			byte[] buf = new byte[512];
			int readByte = 0;
			while ((readByte = in.read(buf)) != -1) {
				out.write(buf, 0, readByte);
			}
			
			//			httpResponse.sendResponseHeaders(HttpStatus.OK.getCode(), 0);

//			InputStream is = getClass().getResourceAsStream(request.getRequestURI()); // class path에서 찾는다. 
//			if (is == null) {
//				throw new FileNotFoundException("파일을 찾을 수 없습니다. " + request.getRequestURI());
//			}
//
//			reader = new InputStreamReader(is, ApplicationContext.getCharsetType());
//			writer = new OutputStreamWriter(response.getResponseBody(), ApplicationContext.getCharsetType());
	
//			char[] buffer = new char[512]; //512바이트를 한번에 읽어와서 쓰도록 함(원래는 1바이트씩)
//			int readCount = 0;
//			while ((readCount = reader.read(buffer)) != -1) {
//원래코드				writer.write(buffer, 0, readCount);
//			}
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			try {
				if (Objects.nonNull(in)) {
					in.close();
				}
				
				if (Objects.nonNull(out)) {
					out.close();
				}
			} catch (IOException e) {
				throw new ViewRenderException(e);
			}
		}
	}
}
