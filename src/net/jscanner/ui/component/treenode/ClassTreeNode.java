package net.jscanner.ui.component.treenode;

import java.util.List;

import net.jscanner.ui.component.ComponentTreeNode;

/**
 * Represents a class as a tree node.
 * 
 * @author Desmond Jackson
 */
public class ClassTreeNode extends ComponentTreeNode {
	
	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6000781209282418810L;
	
	/**
	 * The children methods of the represented class.
	 */
	private List<String> methods;

	/**
	 * Creates a new class tree node representation.
	 * 
	 * @param name The class name
	 * 
	 * @param methods The class methods
	 */
	public ClassTreeNode(String name, List<String> methods) {
		super(name);
		this.methods = methods;
		addChildren();
	}

	@Override
	protected void addChildren() {
		for (String method : methods)
			add(new MethodTreeNode(method));
	}

}
