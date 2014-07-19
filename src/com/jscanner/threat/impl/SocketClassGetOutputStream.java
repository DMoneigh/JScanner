package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the getOutputStream method in the Socket class.
 * 
 * @author Desmond Jackson
 */
public class SocketClassGetOutputStream extends ThreatMethod {

	/**
	 * Creates an instance of the SocketClassGetOutputStream class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public SocketClassGetOutputStream(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("getOutputStream");
	}

	@Override
	public String getReply() {
		return "Data gets written to one or more servers in which the application(s) interact with! (tcp)";
	}

}
