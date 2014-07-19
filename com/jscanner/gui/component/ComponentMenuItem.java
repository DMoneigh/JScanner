package com.jscanner.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * Represents a menu item.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentMenuItem extends JMenuItem
implements ActionListener {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -8396818335787297876L;

	/**
	 * Builds the menu item.
	 * 
	 * @param text The text on the menu item.
	 */
	public ComponentMenuItem(String text) {
		super(text);
		addActionListener(this);
	}

}
