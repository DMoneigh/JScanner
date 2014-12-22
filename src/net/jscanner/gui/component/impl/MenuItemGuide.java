package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Guide" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemGuide extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3364114838038818086L;

	/**
	 * Creates the "Guide" menu item.
	 */
	public MenuItemGuide() {
		super("Guide");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.browse("http://www.jscanner.net");
	}

}
