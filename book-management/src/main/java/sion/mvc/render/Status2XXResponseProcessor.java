package sion.mvc.render;

import com.sun.net.httpserver.HttpExchange;

import sion.mvc.HttpResponse;
import sion.mvc.ResponseProcessor;

public class Status2XXResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse httpResponse) {
	 	//FORWARDING
   	HtmlRender htmlRender = new HtmlRender();
   	htmlRender.render(httpExchange, httpResponse);		
	}

}
