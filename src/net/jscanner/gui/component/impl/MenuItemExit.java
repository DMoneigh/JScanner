package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Exit" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemExit extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 8159360580742399562L;

	/**
	 * Creates the "Exit" menu item.
	 */
	public MenuItemExit() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.getInstance(this).dispose();
	}

}
