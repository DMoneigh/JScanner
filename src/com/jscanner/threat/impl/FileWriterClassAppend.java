package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the append method in the FileWriter class.
 * 
 * @author Desmond Jackson
 */
public class FileWriterClassAppend extends ThreatMethod {

	/**
	 * Creates an instance of the FileWriterClassAppend class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileWriterClassAppend(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("append", new Class<?>[] {char.class});
	}

	@Override
	public String getReply() {
		return "One of more files gets data added onto them.";
	}

}
