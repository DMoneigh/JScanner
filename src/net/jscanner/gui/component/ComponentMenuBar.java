package net.jscanner.gui.component;

import javax.swing.JMenuBar;

/**
 * Represents a menu bar.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenuBar extends JMenuBar {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -4010412805778977460L;
	
	/**
	 * Creates a new menu bar.
	 */
	public ComponentMenuBar() {
		super();
		addMenus();
	}

	/**
	 * Appends menus to this menu bar.
	 */
	public abstract void addMenus();

}
