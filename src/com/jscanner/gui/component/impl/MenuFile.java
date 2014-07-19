package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentMenu;

/**
 * The "File" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuFile extends ComponentMenu {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 839243119003990243L;

	/**
	 * Builds the "File" menu.
	 */
	public MenuFile() {
		super("File");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemPlugins());
		add(new MenuItemExit());
	}

}
