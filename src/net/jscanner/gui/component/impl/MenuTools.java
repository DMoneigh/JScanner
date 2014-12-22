package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Tools" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuTools extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 780948141366626500L;

	/**
	 * Creates the "Tools" menu.
	 */
	public MenuTools() {
		super("Tools");
	}

	@Override
	public void addMenuItems() {
		add(new MenuScan());
		add(new MenuExecute());
	}

}
