package sion.mvc.dispatcher;

import sion.mvc.HttpRequest;
import sion.mvc.HttpResponse;

public interface Controller {
	HttpResponse command(HttpRequest httpRequest);
}
