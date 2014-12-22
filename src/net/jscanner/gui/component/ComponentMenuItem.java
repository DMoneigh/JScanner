package net.jscanner.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Represents a menu item.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenuItem extends JMenuItem 
implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -5416823531067200393L;
	
	/**
	 * Creates a new menu item.
	 * 
	 * @param text The text on the menu item
	 */
	public ComponentMenuItem(String text) {
		super(text);
		addActionListener(this);
	}
	
	/**
	 * Gets the parent menu of this menu item.
	 * 
	 * @return The parent menu of this menu item
	 */
	public JMenu getParentMenu() {
		return (JMenu) ((JPopupMenu) getParent()).getInvoker();
	}
	
}
