package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.JScanner;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Documentation" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemDocumentation extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -5021204768326553684L;

	/**
	 * Builds the "Documentation" menu item.
	 */
	public MenuItemDocumentation() {
		super("Documentation");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.log("http://tinyurl.com/jscanner-documentation");
	}

}
