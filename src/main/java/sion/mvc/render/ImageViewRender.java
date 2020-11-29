package sion.mvc.render;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;
@Slf4j
public class ImageViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		InputStream is = null;
		ServletOutputStream out = null;
      
		try {
			addImageContextHeader(response);
			
			// InputStream으로부터 이미지 읽기
			is = new BufferedInputStream(new FileInputStream((String)mav.getModel().get("imageFullPath")));
			out = response.getOutputStream();
			
			byte[] buf = new byte[512];
			int readByte = 0;
			while ((readByte = is.read(buf)) != -1) {
				out.write(buf, 0, readByte);
			}
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
				try {
					if (Objects.nonNull(is)) {
						is.close();
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
