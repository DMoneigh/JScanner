package com.jscanner.threat.impl;

import java.io.DataOutputStream;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the DataOutputStream class.
 * 
 * @author Desmond Jackson
 */
public class DataOutputStreamClass extends ThreatClass {

	/**
	 * Creates an instance of the DataOutputStreamClass class.
	 */
	public DataOutputStreamClass() {
		super(DataOutputStream.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(DataOutputStreamClassWriteChars.class);
		addThreatMethod(DataOutputStreamClassWriteUTF.class);
	}

	@Override
	public String getReply() {
		return "Creates one or more data output streams"
				+ " so that it can write data to websites, files, and servers!";
	}

}