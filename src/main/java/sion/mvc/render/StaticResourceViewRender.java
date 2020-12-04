package sion.mvc.render;

import java.io.BufferedInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.mvc.ModelAndView;

public class StaticResourceViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
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
			closeStream(in, out);
		}
	}
}
