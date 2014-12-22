package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Source Code" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemSourceCode extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -615198698267593852L;

	/**
	 * Creates the "Source Code" menu item.
	 */
	public MenuItemSourceCode() {
		super("Source Code");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner.browse("http://www.github.com/DMoneigh/JScanner");
	}

}
