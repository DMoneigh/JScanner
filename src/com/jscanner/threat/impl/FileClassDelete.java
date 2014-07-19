package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the dekete method in the File class.
 * 
 * @author Desmond Jackson
 */
public class FileClassDelete extends ThreatMethod {

	/**
	 * Creates an instance of the FileClassDelete class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileClassDelete(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("delete");
	}

	@Override
	public String getReply() {
		return "One or more files get deleted!";
	}

}
