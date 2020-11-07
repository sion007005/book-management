package sion.mvc;

public interface ViewRender {
	void render(HttpRequest httpRequest, HttpResponse httpResponse, ModelAndView mav);
}
