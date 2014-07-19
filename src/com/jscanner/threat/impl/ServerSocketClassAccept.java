package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the accept method in the ServerSocket class.
 * 
 * @author Desmond Jackson
 */
public class ServerSocketClassAccept extends ThreatMethod {

	/**
	 * Creates an instance of the ServerSocketClassAccept class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public ServerSocketClassAccept(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("accept");
	}

	@Override
	public String getReply() {
		return "Other computers are able to remotely access your computer!";
	}

}
