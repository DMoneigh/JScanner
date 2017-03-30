package net.jscanner.ui.component.treenode;

import net.jscanner.ui.component.ComponentTreeNode;

/**
 * Represents a method as a tree node.
 * 
 * @author Desmond Jackson
 */
public class MethodTreeNode extends ComponentTreeNode {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6124136498791729261L;

	/**
	 * Creates a new method as a tree node representation.
	 * 
	 * @param name The method name
	 */
	public MethodTreeNode(String name) {
		super(name);
	}

	@Override
	protected void addChildren() {}

}
