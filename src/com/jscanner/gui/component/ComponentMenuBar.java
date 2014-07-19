package com.jscanner.gui.component;

import javax.swing.JMenuBar;

/**
 * Represents a menu bar.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenuBar extends JMenuBar {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 2050035960511915060L;
	
	/**
	 * Builds the menu bar.
	 */
	public ComponentMenuBar() {
		super();
		addMenus();
	}

	/**
	 * Adds menus to the menu bar.
	 */
	public abstract void addMenus();

}
