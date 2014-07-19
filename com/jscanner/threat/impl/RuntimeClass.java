package com.jscanner.threat.impl;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the Runtime class.
 * 
 * @author Desmond Jackson
 */
public class RuntimeClass extends ThreatClass {

	/**
	 * Creates an instance of the RuntimeClass class.
	 */
	public RuntimeClass() {
		super(Runtime.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(RuntimeClassExec.class);
	}

	@Override
	public String getReply() {
		return "Uses your command prompt!";
	}

}
