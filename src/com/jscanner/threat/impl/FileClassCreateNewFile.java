package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the createNewFile method in the File class.
 * 
 * @author Desmond Jackson
 */
public class FileClassCreateNewFile extends ThreatMethod {

	/**
	 * Creates an instance of the FileClassCreateNewFile class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileClassCreateNewFile(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("createNewFile");
	}

	@Override
	public String getReply() {
		return "A new file gets created!";
	}

}
