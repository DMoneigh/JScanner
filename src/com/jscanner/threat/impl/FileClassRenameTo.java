package com.jscanner.threat.impl;

import java.io.File;
import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the renameTo method in the File class.
 * 
 * @author Desmond Jackson
 */
public class FileClassRenameTo extends ThreatMethod {

	/**
	 * Creates an instance of the FileClassRenameTo class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public FileClassRenameTo(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("renameTo", new Class<?>[] {File.class});
	}

	@Override
	public String getReply() {
		return "One or more files are either renamed or moved to another directory!";
	}

}
