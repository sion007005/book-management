package sion.mvc;

import com.sun.net.httpserver.HttpExchange;

public interface ResponseProcessor {
	void proccess(HttpExchange httpExchange, HttpResponse httpResponse);
}
