package com.jscanner.server.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles requests sent to the web server.
 * 
 * @author Desmond Jackson
 */
public abstract class RequestHandler implements HttpHandler {

	/**
	 * The HTTP session.
	 */
	private HttpExchange exchange;

	/**
	 * The data posted to the web server.
	 */
	protected Map<String, List<String>> postData;

	/**
	 * Writes the response to the HTTP session.
	 * 
	 * @param response The text to write
	 */
	private void writeResponse(String response) {
		try {
			exchange.sendResponseHeaders(200, response.length());
			exchange.getResponseBody().write(response.getBytes());
			exchange.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes when a request is received.
	 * 
	 * @return The response to write to the HTTP session
	 */
	protected abstract String execute();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		this.exchange = exchange;
		postData = new Gson().fromJson(
				new String(IOUtils.toByteArray(exchange.getRequestBody())),
				new TypeToken<Map<String, List<String>>>(){}.getType()
				);
		writeResponse(execute());
	}

}
