package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentMenu;

/**
 * The "Project" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuProject extends ComponentMenu {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 7502657492181295744L;

	/**
	 * Builds the "Project" menu.
	 */
	public MenuProject() {
		super("Project");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemAbout());
		add(new MenuItemContact());
		add(new MenuItemDocumentation());
	}

}
