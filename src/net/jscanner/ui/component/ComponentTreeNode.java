package net.jscanner.ui.component;

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
	private static final long serialVersionUID = -2428041153701958192L;
	
	/**
	 * Creates a new tree node.
	 * 
	 * @param name The name
	 */
	public ComponentTreeNode(String name) {
		super(name);
	}
	
	/**
	 * Appends children to the tree node.
	 */
	protected abstract void addChildren();

}
