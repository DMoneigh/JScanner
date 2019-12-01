package com.jscanner.ui.component;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

/**
 * Represents a menu item.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenuItem extends JMenuItem implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -4412084707254300909L;
	
	/**
	 * Creates a new menu item.
	 * 
	 * @param name The menu item name
	 */
	public ComponentMenuItem(String name) {
		super(name);
		setIcon(getImageIcon());
		addActionListener(this);
	}
	
	/**
	 * Gets the image icon for the menu item.
	 * 
	 * @return The image icon
	 */
	public abstract ImageIcon getImageIcon();

}
