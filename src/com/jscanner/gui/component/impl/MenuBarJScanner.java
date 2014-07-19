package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentMenuBar;

/**
 * The menu bar of the JScanner graphical user interface.
 * 
 * @author Desmond Jackson
 */
public class MenuBarJScanner extends ComponentMenuBar {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -5101419840294927532L;

	/**
	 * Builds the JScanner menu bar.
	 */
	public MenuBarJScanner() {
		super();
	}

	@Override
	public void addMenus() {
		add(new MenuFile());
		add(new MenuEdit());
		add(new MenuScan());
		add(new MenuProject());
	}

}
