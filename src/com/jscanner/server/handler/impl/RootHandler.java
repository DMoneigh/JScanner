package com.jscanner.server.handler.impl;

import java.util.Date;

import com.jscanner.server.handler.RequestHandler;

/**
 * Represents the "/" (root) directory on the web server.
 * 
 * @author Desmond Jackson
 */
public class RootHandler extends RequestHandler {

	@Override
	protected String execute() {
		return "System Date: " + new Date(System.currentTimeMillis());
	}

}
