package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Exit" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemExit extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 5247048678344606305L;

	/**
	 * Builds the "Exit" menu item.
	 */
	public MenuItemExit() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
