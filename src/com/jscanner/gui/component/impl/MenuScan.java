package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentMenu;

/**
 * The "Scan" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuScan extends ComponentMenu {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 1752044671297065094L;

	/**
	 * Builds the "Scan" menu.
	 */
	public MenuScan() {
		super("Scan");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemApplet());
		add(new MenuItemApplication());
		add(new MenuItemComputer());
	}

}
