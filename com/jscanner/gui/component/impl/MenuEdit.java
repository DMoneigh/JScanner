package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentMenu;

/**
 * The "Edit" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuEdit extends ComponentMenu {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -4848473662505616987L;

	/**
	 * Builds the "Edit" menu.
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
