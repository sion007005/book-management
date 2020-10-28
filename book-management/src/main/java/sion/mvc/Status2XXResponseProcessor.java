package sion.mvc;

import com.sun.net.httpserver.HttpExchange;

public class Status2XXResponseProcessor implements ResponseProcessor {

	@Override
	public void proccess(HttpExchange httpExchange, HttpResponse<?> httpResponse) {
	 	//FORWARDING
   	HtmlRender htmlRender = new HtmlRender();
   	htmlRender.render(httpExchange, httpResponse);		
	}

}
