package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the writeUTF method in the DatagramOutputStream class.
 * 
 * @author Desmond Jackson
 */
public class DataOutputStreamClassWriteUTF extends ThreatMethod {

	/**
	 * Creates an instance of the DataOutputStreamClassWriteUTF class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public DataOutputStreamClassWriteUTF(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("writeUTF", new Class<?> [] {String.class});
	}

	@Override
	public String getReply() {
		return "Words are being written to this stream!";
	}

}
