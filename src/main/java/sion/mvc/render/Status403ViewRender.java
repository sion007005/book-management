package sion.mvc.render;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.http.ServerRunnerException;
import sion.mvc.ModelAndView;

@Slf4j
public class Status403ViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		try {
			String backPageURI = request.getRequestURI();
			String id = request.getParameter("id");
			
			if (Objects.nonNull(id)) {
				Cookie memberIdCookie = new Cookie("backPageInfoId", id);
				memberIdCookie.setDomain("localhost");
				memberIdCookie.setPath("/");
				response.addCookie(memberIdCookie);
			}
			
			Cookie backPageURICookie = new Cookie("backPageURI", backPageURI);
			backPageURICookie.setDomain("localhost");
			backPageURICookie.setPath("/");
			response.addCookie(backPageURICookie);
			
			response.sendRedirect("/login/form");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServerRunnerException(e);
		}
	}
}
