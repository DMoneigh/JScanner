package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the getInputStream method in the Socket class.
 * 
 * @author Desmond Jackson
 */
public class SocketClassGetInputStream extends ThreatMethod {

	/**
	 * Creates an instance of the SocketClassGetInputStream class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public SocketClassGetInputStream(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("getInputStream");
	}

	@Override
	public String getReply() {
		return "Data gets read from the one or more servers in which the application(s) interact with! (tcp)";
	}

}
