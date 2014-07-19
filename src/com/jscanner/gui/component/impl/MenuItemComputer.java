package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.io.IOException;

import com.jscanner.archive.ArchiveFinder;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Computer" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemComputer extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -6217445914400012110L;

	/**
	 * Builds the "Computer" menu item.
	 */
	public MenuItemComputer() {
		super("Computer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ArchiveFinder.findArchives();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
