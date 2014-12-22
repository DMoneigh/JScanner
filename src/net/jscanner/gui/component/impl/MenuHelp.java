package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Help" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuHelp extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3221192975057458843L;

	/**
	 * Creates the "Help" menu.
	 */
	public MenuHelp() {
		super("Help");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemGuide());
	}

}
