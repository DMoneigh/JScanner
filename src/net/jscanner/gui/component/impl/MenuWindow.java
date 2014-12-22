package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenu;

/**
 * The "Window" menu.
 * 
 * @author Desmond Jackson
 */
public class MenuWindow extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -3134099229317335480L;
	
	/**
	 * Creates the "Window" menu.
	 */
	public MenuWindow() {
		super("Window");
	}

	@Override
	public void addMenuItems() {
		add(new MenuItemNewWindow());
		add(new CheckBoxAlwaysOnTop());
		add(new CheckBoxResizable());
	}

}
