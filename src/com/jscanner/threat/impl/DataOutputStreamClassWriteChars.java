package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the writeChars method in the DatagramOutputStream class.
 * 
 * @author Desmond Jackson
 */
public class DataOutputStreamClassWriteChars extends ThreatMethod {

	/**
	 * Creates an instance of the DataOutputStreamClassWriteChars class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public DataOutputStreamClassWriteChars(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("writeChars", new Class<?>[] {String.class});
	}

	@Override
	public String getReply() {
		return "Characters are being written to this stream.";
	}

}
