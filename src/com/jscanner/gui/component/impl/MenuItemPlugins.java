package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.gui.component.ComponentMenuItem;
import com.jscanner.gui.impl.GuiPlugins;

/**
 * The "Plugins" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemPlugins extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 3907570232629388940L;

	/**
	 * Builds the "Plugins" menu item.
	 */
	public MenuItemPlugins() {
		super("Plugins");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new GuiPlugins().setVisible(true);
	}

}
