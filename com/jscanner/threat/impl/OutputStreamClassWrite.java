package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the write method in the OutputStream class.
 * 
 * @author Desmond Jackson
 */
public class OutputStreamClassWrite extends ThreatMethod {

	/**
	 * Creates an instance of the OutputStreamClassWrite class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public OutputStreamClassWrite(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("write", new Class<?>[] {int.class});
	}

	@Override
	public String getReply() {
		return "One or more output streams are writing data to either"
				+ " files, websites, or servers!";
	}

}
