package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Scan" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuScan extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6743364403881510563L;

	/**
	 * Creates the "Scan" menu.
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
