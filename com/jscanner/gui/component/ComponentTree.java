package com.jscanner.gui.component;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

/**
 * Represents a tree.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTree extends JTree {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -4400082785955581482L;

	/**
	 * Builds the tree.
	 */
	public ComponentTree() {
		super();
		setModel(createTreeModel());
	}
	
	/**
	 * Creates a tree model.
	 * 
	 * @return The tree model.
	 */
	public abstract TreeModel createTreeModel();
	
}
