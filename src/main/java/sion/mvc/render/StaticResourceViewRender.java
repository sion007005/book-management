package sion.mvc.render;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;

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
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			//TODO in/out close 시켜주는 메소드를 만들어 사용하자.
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
