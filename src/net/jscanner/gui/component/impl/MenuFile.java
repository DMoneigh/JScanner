package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "File" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuFile extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6256232148027418724L;

	/**
	 * Creates the "File" menu.
	 * 
	 */
	public MenuFile() {
		super("File");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemExit());
	}

}
