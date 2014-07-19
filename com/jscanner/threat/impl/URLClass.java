package com.jscanner.threat.impl;

import java.net.URL;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the URL class.
 * 
 * @author Desmond Jackson
 */
public class URLClass extends ThreatClass {

	/**
	 * Creates an instance of the URLClass class.
	 */
	public URLClass() {
		super(URL.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(URLClassOpenConnection.class);
		addThreatMethod(URLClassOpenStream.class);
	}

	@Override
	public String getReply() {
		return "Interacts with websites!";
	}

}
