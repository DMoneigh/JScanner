package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the read method in the InputStream class.
 * 
 * @author Desmond Jackson
 */
public class InputStreamClassRead extends ThreatMethod {

	/**
	 * Creates an instance of the InputStreamClassRead class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public InputStreamClassRead(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("read");
	}

	@Override
	public String getReply() {
		return "One or more input streams are reading data from either"
				+ " files, websites, or servers!";
	}

}
