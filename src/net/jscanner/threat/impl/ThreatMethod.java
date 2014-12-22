package net.jscanner.threat.impl;

import org.objectweb.asm.tree.MethodNode;

import net.jscanner.threat.ThreatObject;

/**
 * Represents a threatening method.
 * 
 * @author Desmond Jackson
 */
public class ThreatMethod extends ThreatObject {

	/**
	 * Creates the threatening method representation.
	 * 
	 * @param methodNode The threatening method to represent
	 */
	public ThreatMethod(MethodNode methodNode) {
		super(methodNode);
	}

	@Override
	public String getName() {
		return ((MethodNode) object).name;
	}

}
