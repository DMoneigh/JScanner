package net.jscanner.gui.component;

import javax.swing.JMenu;

/**
 * Represents a menu.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenu extends JMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -5461842002668104615L;
	
	/**
	 * Creates a new menu.
	 * 
	 * @param text The text on the menu
	 */
	public ComponentMenu(String text) {
		super(text);
		addMenuItems();
	}
	
	/**
	 * Appends menu items to this menu.
	 */
	public abstract void addMenuItems();

}
