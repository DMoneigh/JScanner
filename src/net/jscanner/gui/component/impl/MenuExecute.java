package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Execute" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuExecute extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 1371701215212088310L;

	/**
	 * Creates the "Execute" menu.
	 */
	public MenuExecute() {
		super("Execute");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemApplet());
		add(new MenuItemApplication());
	}

}
