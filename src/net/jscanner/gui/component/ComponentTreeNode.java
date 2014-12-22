package net.jscanner.gui.component;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Represents a tree node.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTreeNode extends DefaultMutableTreeNode {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -8335265173024899218L;
	
	/**
	 * Creates a new tree node.
	 * 
	 * @param The text to appear on the node
	 */
	public ComponentTreeNode(String text) {
		super(text);
	}
	
	/**
	 * Appends nodes to the children of this node.
	 */
	public abstract void addChildren();

}
