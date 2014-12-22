package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Edit" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuEdit extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3118967365739013298L;

	/**
	 * Creates the "Edit" menu.
	 */
	public MenuEdit() {
		super("Edit");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemCopy());
		add(new MenuItemClear());
	}

}
