package sion.bookmanagement.controller;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public class CategoryFormController implements Controller {

	@Override
	public HttpResponse<?> command(HttpRequest httpRequest) {
		return new HttpResponse<>(null, "category_form");
	}

}
