package com.jscanner.threat.impl;

import java.io.DataInputStream;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the DataInputStream class.
 * 
 * @author Desmond Jackson
 */
public class DataInputStreamClass extends ThreatClass {

	/**
	 * Creates an instance of the DataInputStreamClass class.
	 */
	public DataInputStreamClass() {
		super(DataInputStream.class);
	}

	@Override
	public void addThreatMethods() {}

	@Override
	public String getReply() {
		return "Creates one or more data input streams"
				+ " so that it can read data from websites, files, and servers!";
	}

}
