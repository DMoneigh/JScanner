package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.JScanner;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Clear" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemClear extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -6648777182681310962L;

	/**
	 * Builds the "Clear" menu item.
	 */
	public MenuItemClear() {
		super("Clear");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.clearLog();
	}

}
