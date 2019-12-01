package com.jscanner.ui.component;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

/**
 * Represents a tree.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTree extends JTree {
	
	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 5854487608440834678L;

	/**
	 * Creates a new tree.
	 */
	public ComponentTree() {
		setModel(getTreeModel());
		getSelectionModel().setSelectionMode(getCustomSelectionMode());
	}
	
	/**
	 * Gets the custom tree model.
	 * 
	 * @return The custom tree model
	 */
	public abstract TreeModel getTreeModel();
	
	/**
	 * Gets the custom selection mode.
	 * 
	 * @return The selection mode
	 */
	public abstract int getCustomSelectionMode();

}
