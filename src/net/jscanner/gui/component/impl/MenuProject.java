package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Project" menu.
 * 
 * @author desmond
 */
public class MenuProject extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 780948141366626500L;

	/**
	 * Creates the "Project" menu.
	 */
	public MenuProject() {
		super("Project");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemLog());
		add(new MenuItemSourceCode());
	}

}
