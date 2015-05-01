package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "New Window" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemNewWindow extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 5528271920592958563L;

	/**
	 * Creates the "New Window" menu item.
	 */
	public MenuItemNewWindow() {
		super("New Window");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				new JScanner().setVisible(true);
			}
			
		}).start();
	}

}
