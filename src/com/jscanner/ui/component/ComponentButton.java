package com.jscanner.ui.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Represents a button.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentButton extends JButton implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -2392315516934821338L;
	
	/**
	 * Creates a button.
	 * 
	 * @param text The text
	 */
	public ComponentButton(String text) {
		super(text);
		addActionListener(this);
	}

}
