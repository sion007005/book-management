package sion.mvc.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.UriParser;

@Slf4j
public class HttpUtils {
	public static Map<String, Object> makeParameters(HttpExchange httpExchange) throws UnsupportedEncodingException {
		URI uri = httpExchange.getRequestURI(); // localhost:3333?id=1&name=AAA라면, id=1&name=AAA로 자른다.
		String uriString = uri.toString();
		String queryString = null;

		if (uriString.contains("?")) {
			queryString = uriString.substring(uriString.indexOf("?") + 1, uriString.length());
		}

		log.info("query string : " + queryString);

		Map<String, Object> parameters = new HashMap<>();

		UriParser uriParser = new UriParser();
		uriParser.parseQuery(queryString, parameters);
		parameters.forEach((k, v) -> log.info("key : " + k + " / value : " + v));

		return parameters;
	}

	public static Map<String, Object> makeAttributes(HttpExchange httpExchange) throws IOException {
		String inputLine = "";
		String resultStr = "";

		InputStream in = httpExchange.getRequestBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

		StringBuffer response = new StringBuffer();

		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}

		String bodyString = response.toString();
		log.info("body string : " + bodyString);

		Map<String, Object> attributes = new HashMap<>();

		UriParser uriParser = new UriParser();
		uriParser.parseQuery(bodyString, attributes);
		attributes.forEach((k, v) -> log.info("key : " + k + " / value : " + v));

		return attributes;
	}

	public static String makeUriPath(HttpExchange httpExchange) {
		URI uri = httpExchange.getRequestURI();
		return uri.getPath(); // 파라미터를 제외한 Path를 받아온다 (books/list 이런식)
	}
}
