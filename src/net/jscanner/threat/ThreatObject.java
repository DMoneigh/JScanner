package net.jscanner.threat;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a threatening object.
 * 
 * @author Desmond Jackson
 */
public abstract class ThreatObject extends Object {
	
	/**
	 * The threatening object.
	 */
	protected Object object;
	
	/**
	 * The children of this threat object.
	 */
	protected List<ThreatObject> children;
	
	/**
	 * The state of this threat object.
	 */
	private boolean used;
	
	/**
	 * The text to be displayed when the state of this threat object is true.
	 */
	private String output;
	
	/**
	 * Creates the threatening object representation.
	 * 
	 * @param object The threatening object to represent
	 */
	public ThreatObject(Object object) {
		super();
		this.object = object;
		children = new ArrayList<ThreatObject>();
		setOutput(getName() + " is used.");
	}
	
	/**
	 * Appends a child to the list of children.
	 * 
	 * @param threatObject The child
	 */
	protected void addChild(ThreatObject threatObject) {
		children.add(threatObject);
	}
	
	/**
	 * Sets the state of this threat object to true.
	 */
	public void setUsed() {
		used = true;
	}
	
	/**
	 * Sets the output to the specified text.
	 * 
	 * @param output The specified text
	 */
	public void setOutput(String output) {
		this.output = output;
	}
	
	/**
	 * Gets the state of this threat object.
	 * 
	 * @return false if this threat object is not used
	 */
	public boolean isUsed() {
		return used;
	}
	
	/**
	 * Gets the output of this threat object.
	 * 
	 * @return The output of this threat object
	 */
	public String getOutput() {
		return output;
	}
	
	/**
	 * Gets the name of this threat object.
	 * 
	 * @return The name of this threat object
	 */
	public abstract String getName();
	
}
