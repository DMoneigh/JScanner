package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the write method in the FileWriter class.
 * 
 * @author Desmond Jackson
 */
public class FileWriterClassWrite extends ThreatMethod {

	/**
	 * Creates an instance of the FileWriterClassWrite class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileWriterClassWrite(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("write", new Class<?>[] {String.class});
	}

	@Override
	public String getReply() {
		return "One or more files get cleared, and new data gets written onto them!";
	}

}
