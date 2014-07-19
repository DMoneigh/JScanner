package com.jscanner.gui.component;

import javax.swing.JTextArea;

/**
 * Represents a text area.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTextArea extends JTextArea {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 3250138293285856558L;

	/**
	 * Builds the text area.
	 */
	public ComponentTextArea() {
		super();
		configure();
	}
	
	/**
	 * Configure the settings of the text area.
	 */
	public abstract void configure();
	
	@Override
	public void append(String string) {
		super.append(string + "\n");
	}
	
}
