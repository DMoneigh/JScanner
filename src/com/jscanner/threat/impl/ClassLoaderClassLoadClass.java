package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the loadClass method in the ClassLoader class.
 * 
 * @author Desmond Jackson
 */
public class ClassLoaderClassLoadClass extends ThreatMethod {

	/**
	 * Creates an instance of the ClassLoaderClassLoadClass class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public ClassLoaderClassLoadClass(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("loadClass", new Class<?>[] {String.class});
	}

	@Override
	public String getReply() {
		return "Attempting to use the code from the read Java program!";
	}

}
