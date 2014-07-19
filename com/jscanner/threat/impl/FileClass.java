package com.jscanner.threat.impl;

import java.io.File;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the File class.
 * 
 * @author Desmond Jackson
 */
public class FileClass extends ThreatClass {

	/**
	 * Creates an instance of the FileClass class.
	 */
	public FileClass() {
		super(File.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(FileClassCreateNewFile.class);
		addThreatMethod(FileClassDelete.class);
		addThreatMethod(FileClassDeleteOnExit.class);
		addThreatMethod(FileClassMkdir.class);
		addThreatMethod(FileClassMkdirs.class);
		addThreatMethod(FileClassRenameTo.class);
	}

	@Override
	public String getReply() {
		return "Interacts with one or more files!";
	}

}
