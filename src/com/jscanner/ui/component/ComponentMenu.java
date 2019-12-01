package com.jscanner.ui.component;

import javax.swing.JMenu;

/**
 * Represents a menu.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenu extends JMenu {
	
	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -9212498446073183452L;
	
	/**
	 * Creates a new menu.
	 * 
	 * @param name The menu name
	 */
	public ComponentMenu(String name) {
		super(name);
		addMenuItems();
	}
	
	/**
	 * Appends menu items to the menu.
	 */
	protected abstract void addMenuItems();

}
