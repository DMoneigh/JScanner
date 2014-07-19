package com.jscanner.threat;

import java.lang.reflect.Method;

/**
 * Represents a threatening method.
 * 
 * @author Desmond Jackson
 */
public abstract class ThreatMethod extends ThreatObject {
	
	/**
	 * The threatening class that this method is located in.
	 */
	protected ThreatClass owner;
	
	/**
	 * The method that this threatening method represents.
	 */
	private Method method;
	
	/**
	 * Creates an instance of the ThreatMethod class.
	 * 
	 * @param owner The threatening class that this method is located in.
	 */
	public ThreatMethod(ThreatClass owner) {
		super();
		this.owner = owner;
		this.method = getMethod();
	}
	
	/**
	 * Gets the method that this threatening method represents.
	 * 
	 * @return The method that this threatening method represents.
	 */
	public abstract Method getMethod();
	
	@Override
	public String getName() {
		return method.getName();
	}

}
