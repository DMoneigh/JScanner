package net.jscanner.gui.component.impl;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import net.jscanner.gui.component.ComponentTreeNode;

/**
 * A tree node that represents a threatening object.
 * 
 * @author Desmond Jackson
 */
public class TreeNodeThreat extends ComponentTreeNode {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6940942485998053390L;

	/**
	 * The threatening object to represent.
	 */
	private Object object;

	/**
	 * Creates a new tree node that represents a threatening object.
	 * 
	 * @param text The text to appear on the tree
	 * 
	 * @param object The threatening object to represent
	 */
	public TreeNodeThreat(String text, Object object) {
		super(text);
		this.object = object;
		addChildren();
	}

	/**
	 * Gets the threatening object that this tree node represents.
	 * 
	 * @return The threatening object that this tree node represents
	 */
	public Object getRepresentation() {
		return object;
	}

	@Override
	public void addChildren() {
		if (object instanceof ClassNode) 
			for (Object object : ((ClassNode) object).methods)
				if (object instanceof MethodNode) {
					MethodNode methodNode = (MethodNode) object;
					if (methodNode.name.equals("<init>"))
						continue;
					add(new TreeNodeThreat(methodNode.name, methodNode));
				}
	}

}
