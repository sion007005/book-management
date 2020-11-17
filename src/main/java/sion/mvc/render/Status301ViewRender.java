package sion.mvc.render;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;

@Slf4j
public class Status301ViewRender implements ViewRender {
	
	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		// REDIRECT
		try {
			response.sendRedirect(mav.getRedirectURI());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
