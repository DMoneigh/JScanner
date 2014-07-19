package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.JScanner;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Contact" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemContact extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -1935584661585619335L;

	/**
	 * The "Contact" menu item.
	 */
	public MenuItemContact() {
		super("Contact");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.log("Skype: dmoneigh or email: dmoneigh@gmail.com");
	}

}
