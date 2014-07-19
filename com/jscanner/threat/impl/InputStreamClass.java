package com.jscanner.threat.impl;

import java.io.InputStream;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the InputStream class.
 * 
 * @author Desmond Jackson
 */
public class InputStreamClass extends ThreatClass {

	/**
	 * Creates an instance of the InputStreamClass class.
	 */
	public InputStreamClass() {
		super(InputStream.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(InputStreamClassRead.class);
	}

	@Override
	public String getReply() {
		return "Creates one or more input streams so that it can read data "
				+ "from websites, files, and servers!";
	}

}
