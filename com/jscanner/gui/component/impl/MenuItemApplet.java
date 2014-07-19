package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import com.jscanner.JScanner;
import com.jscanner.archive.Archive;
import com.jscanner.archive.ArchiveScanner;
import com.jscanner.gui.component.ComponentMenuItem;
import com.jscanner.util.WebPage;

/**
 * The "Applet" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemApplet extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 1780770195839993355L;

	/**
	 * Builds the "Applet" menu item.
	 */
	public MenuItemApplet() {
		super("Applet");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String url = JOptionPane.showInputDialog("Enter url below.");
		if (url != null) {
			JScanner.log("Looking for archives on: " + url);
			List<Archive> archives = new WebPage(url).getArchives();
			if (archives.size() > 0)
				for (Archive archive : archives)
					new ArchiveScanner(archive);
			else
				JScanner.log("Could not find any archives on: " + url);
		}
	}

}
