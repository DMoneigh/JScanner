package net.jscanner.threat.impl;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;

import net.jscanner.threat.ThreatObject;

/**
 * Represents a threatening class.
 * 
 * @author Desmond Jackson
 */
public class ThreatClass extends ThreatObject {
	
	/**
	 * A list of objects that this threat class interacts with.
	 */
	private List<String> interactions;

	/**
	 * Creates the threatening class representation.
	 * 
	 * @param clazz The threatening class to represent
	 */
	public ThreatClass(ClassNode clazz) {
		super(clazz);
		interactions = new ArrayList<String>();
	}
	
	/**
	 * Appends a threat method to the children of this threat class.
	 * 
	 * @param threatMethod The threat method to append
	 */
	public void addThreatMethod(ThreatMethod threatMethod) {
		addChild(threatMethod);
	}
	
	/**
	 * Appends an interaction to the list of interactions.
	 * 
	 * @param interaction The interaction to append
	 */
	public void addInteraction(String interaction) {
		interactions.add("Interaction: " + interaction);
	}
	
	/**
	 * Gets the children threat methods of this threat class.
	 * 
	 * @return The children threat methods of this threat class
	 */
	public ThreatMethod[] getThreatMethods() {
		return children.toArray(new ThreatMethod[children.size()]);
	}
	
	/**
	 * Gets the interactions of this threat class.
	 * 
	 * @return The interactions of this threat class
	 */
	public String[] getInteractions() {
		return interactions.toArray(new String[interactions.size()]);
	}
	
	/**
	 * Gets the object that this threat class represents.
	 * 
	 * @return The object that this threat class represents
	 */
	public ClassNode getRepresentation() {
		return (ClassNode) object;
	}
	
	@Override
	public String getName() {
		return ((ClassNode) object).name;
	}

}
