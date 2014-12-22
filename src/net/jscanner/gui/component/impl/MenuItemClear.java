package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Clear" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemClear extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6628908759293034886L;

	/**
	 * Creates the "Clear" menu item.
	 */
	public MenuItemClear() {
		super("Clear");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.getInstance(this).clearConsole();
	}

}
