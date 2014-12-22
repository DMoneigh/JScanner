package net.jscanner.gui.component;

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
	private static final long serialVersionUID = -7403490046374694196L;
	
	/**
	 * Creates a new tree.
	 */
	public ComponentTree() {
		super();
		setModel(getTreeModel());
		getSelectionModel().setSelectionMode(getSelectionMode());
	}
	
	/**
	 * Gets a custom tree model.
	 * 
	 * @return A custom tree model
	 */
	public abstract TreeModel getTreeModel();
	
	/**
	 * Gets a custom tree selection mode.
	 * 
	 * @return A custom tree selection mode
	 */
	public abstract int getSelectionMode();

}
