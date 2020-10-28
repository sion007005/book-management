package sion.bookmanagement.controller;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public interface Controller {
	HttpResponse<?> command(HttpRequest httpRequest);
}
