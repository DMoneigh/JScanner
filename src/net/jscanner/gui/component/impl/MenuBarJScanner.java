package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentMenuBar;

/**
 * The JScanner menu bar.
 * 
 * @author Desmond Jackson
 */
public class MenuBarJScanner extends ComponentMenuBar {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -646596105350108002L;
	
	/**
	 * Creates the JScanner menu bar.
	 */
	public MenuBarJScanner() {
		super();
	}

	@Override
	public void addMenus() {
		add(new MenuFile());
		add(new MenuEdit());
		add(new MenuTools());
		add(new MenuProject());
		add(new MenuWindow());
		add(new MenuHelp());
	}

}
