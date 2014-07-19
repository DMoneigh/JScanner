package com.jscanner.threat;

import java.util.List;

/**
 * The threat object interface.
 * 
 * @author Desmond Jackson
 */
public interface ThreatImpl {

	/**
	 * Sets a threat object to the state of being used.
	 */
	public abstract void setUsed();

	/**
	 * Adds a threat object into the threat object list.
	 * 
	 * @param threatObject The threat object to add to the threat list.
	 */
	public abstract void addThreatObject(Class<? extends ThreatObject> threatObject);

	/**
	 * Gets all threat objects in the threat object list.
	 * 
	 * @return All threat objects in the threat object list.
	 */
	public abstract List<Class<? extends ThreatObject>> getThreatObjects();

	/**
	 * Checks the state of the threat object.
	 * 
	 * @return The state of the threat object.
	 */
	public abstract boolean isUsed();

	/**
	 * Gets the name of a threat object.
	 * 
	 * @return The name of a threat object.
	 */
	public abstract String getName();

}
