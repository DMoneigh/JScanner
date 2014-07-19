package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import com.jscanner.archive.Archive;
import com.jscanner.archive.ArchiveScanner;
import com.jscanner.gui.component.ComponentMenuItem;
import com.jscanner.util.FileManager;

/**
 * The "Application" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemApplication extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 7501648013168982367L;

	/**
	 * Builds the "Application" menu item.
	 */
	public MenuItemApplication() {
		super("Application");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Archive archive = FileManager.getSelectedArchive(this);
		if (archive != null)
			new ArchiveScanner(archive);
	}

}
