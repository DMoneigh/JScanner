package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the read method in the FileReader class.
 * 
 * @author Desmond Jackson
 */
public class FileReaderClassRead extends ThreatMethod {

	/**
	 * Creates an instance of the FileReaderClassRead class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileReaderClassRead(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("read");
	}

	@Override
	public String getReply() {
		return "One or more files gets its data read!";
	}

}
