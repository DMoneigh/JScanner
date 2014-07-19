package com.jscanner.threat;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a threat object.
 * 
 * @author Desmond Jackson
 */
public abstract class ThreatObject extends Object implements ThreatImpl {
	
	/**
	 * A list of all threat objects.
	 */
	private List<Class<? extends ThreatObject>> threatObjects;
	
	/**
	 * The state of the the threat object.
	 */
	private boolean used;
	
	/**
	 * A list of interactions.
	 */
	private List<String> interactions;
	
	/**
	 * Creates the threat object representation.
	 */
	public ThreatObject() {
		super();
		threatObjects = new ArrayList<Class<? extends ThreatObject>>();
		interactions = new ArrayList<String>();
	}
	
	/**
	 * Adds an interaction to the list of interactions.
	 * 
	 * @param interaction The interaction to add.
	 */
	public void addInteraction(String interaction) {
		interactions.add(interaction);
	}
	
	/**
	 * Gets the interactions of the threat object.
	 * 
	 * @return The interactions of the threat object.
	 */
	public List<String> getInteractions() {
		return interactions;
	}
	
	/**
	 * Gets the message that will appear in the graphical user interface.
	 * 
	 * @return The message that will appear in the graphical user interface.
	 */
	public abstract String getReply();
	
	@Override
	public void setUsed() {
		used = true;
	}
	
	@Override
	public void addThreatObject(Class<? extends ThreatObject> threatObject) {
		threatObjects.add(threatObject);
	}
	
	@Override
	public List<Class<? extends ThreatObject>> getThreatObjects() {
		return threatObjects;
	}
	
	@Override
	public boolean isUsed() {
		return used;
	}
	
}
