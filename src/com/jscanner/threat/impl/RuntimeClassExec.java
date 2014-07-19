package com.jscanner.threat.impl;

import java.lang.reflect.Method;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the exec method in the Runtime class.
 * 
 * @author Desmond Jackson
 */
public class RuntimeClassExec extends ThreatMethod {

	/**
	 * Creates an instance of the RuntimeClassExec class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public RuntimeClassExec(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("exec", new Class<?>[] {String.class});
	}

	@Override
	public String getReply() {
		return "One or more commands are executed through your command prompt!";
	}

}
