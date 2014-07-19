package com.jscanner.gui.component.impl;

import com.jscanner.gui.component.ComponentTextArea;

/**
 * The console of JScanner graphical user interface.
 * 
 * @author Desmond Jackson
 */
public class TextAreaConsole extends ComponentTextArea {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -4366110384948140146L;
	
	/**
	 * Builds the console.
	 */
	public TextAreaConsole() {
		super();
	}

	@Override
	public void configure() {
		setEditable(false);
	}

}
