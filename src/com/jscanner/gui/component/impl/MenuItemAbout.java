package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.JScanner;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "About" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemAbout extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 1351180745598245199L;

	/**
	 * Build the "About" menu item.
	 */
	public MenuItemAbout() {
		super("About");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.log("This application is a Java Malware Defense Tool.");
	}

}
