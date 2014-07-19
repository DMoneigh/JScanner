package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the mdir method in the File class.
 * 
 * @author Desmond Jackson
 */
public class FileClassMkdir extends ThreatMethod {

	/**
	 * Creates an instance of the FileClassMkdir class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileClassMkdir(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("mkdir");
	}

	@Override
	public String getReply() {
		return "One or more directories get created!";
	}

}
