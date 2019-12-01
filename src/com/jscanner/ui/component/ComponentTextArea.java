package com.jscanner.ui.component;

import javax.swing.JTextArea;

/**
 * Represents a text area.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTextArea extends JTextArea {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -5328683641557967031L;
	
	/**
	 * Creates a new text area.
	 */
	public ComponentTextArea() {
		setEditable(false);
	}
	
	@Override
	public void append(String string) {
		super.append(string + "\n");
	}

}
