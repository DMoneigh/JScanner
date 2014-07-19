package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the openConnection method in the URL class.
 * 
 * @author Desmond Jackson
 */
public class URLClassOpenConnection extends ThreatMethod {

	/**
	 * Creates an instance of the URLClassOpenConnection class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public URLClassOpenConnection(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("openConnection");
	}

	@Override
	public String getReply() {
		return "One or more connections are created so that the scanned application(s) can "
				+ "read/write data.";
	}

}
