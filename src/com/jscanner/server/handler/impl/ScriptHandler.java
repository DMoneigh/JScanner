package com.jscanner.server.handler.impl;

import com.jscanner.server.handler.RequestHandler;
import com.jscanner.util.FileManager;

/**
 * Represents the "/script.js" file on the web server.
 * 
 * @author Desmond Jackson
 */
public class ScriptHandler extends RequestHandler {

	@Override
	protected String execute() {
		return FileManager.contents("script.js");
	}
	
}
