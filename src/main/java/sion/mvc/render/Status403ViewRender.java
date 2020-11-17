package sion.mvc.render;

import java.util.Objects;

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
			String queryString = request.getQueryString();
			String returnUrl = request.getRequestURL().toString();
			String redirectPath = "/login/form?returnUrl=" + returnUrl;

			if (Objects.nonNull(queryString)) {
				redirectPath += "?" + queryString;
			}
			log.debug("redirectPath : {}",redirectPath);
			response.sendRedirect(redirectPath);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServerRunnerException(e);
		}
	}
}
