package com.jscanner.ui.component;

import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

/**
 * Represents a check box.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentCheckBox extends JCheckBox implements ItemListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6790897745969388743L;
	
	/**
	 * Creates a new check box.
	 * 
	 * @param text The text to appear next to the check box
	 */
	public ComponentCheckBox(String text) {
		super(text);
		addItemListener(this);
	}

}
