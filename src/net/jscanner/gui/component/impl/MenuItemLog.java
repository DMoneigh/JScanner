package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Log" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemLog extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 5920950100005465241L;

	/**
	 * Creates the "Log" menu item.
	 */
	public MenuItemLog() {
		super("Log");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.browse("http://www.tinyurl.com/jscanner-log");
	}

}
