package com.jscanner.gui.component;

import javax.swing.JMenu;

/**
 * Represents a menu.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenu extends JMenu {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -5705821552106094136L;

	/**
	 * Builds the menu.
	 * 
	 * @param text The text on the menu.
	 */
	public ComponentMenu(String text) {
		super(text);
		addMenuItems();
	}

	/**
	 * Adds menu items to the menu.
	 */
	public abstract void addMenuItems();

}
