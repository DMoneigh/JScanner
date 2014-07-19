package com.jscanner.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Represents a button.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentButton extends JButton implements ActionListener {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -4787269767313844157L;

	/**
	 * Builds the button.
	 * 
	 * @param text The text on the button.
	 */
	public ComponentButton(String text) {
		super(text);
		addActionListener(this);
	}

}
