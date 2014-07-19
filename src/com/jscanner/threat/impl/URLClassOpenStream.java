package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the openStream method in the URL class.
 * 
 * @author Desmond Jackson
 */
public class URLClassOpenStream extends ThreatMethod {

	/**
	 * Creates an instance of the URLClassOpenStream class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public URLClassOpenStream(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("openStream");
	}

	@Override
	public String getReply() {
		return "One or more streams are created to a website so that the scanned application can "
				+ "read data!";
	}

}
