package net.jscanner.gui.component.impl;

import net.jscanner.gui.component.ComponentTextArea;

/**
 * The JScanner console.
 * 
 * @author Desmond Jackson
 */
public class TextAreaConsole extends ComponentTextArea {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3723601776765438438L;
	
	/**
	 * Creates a new console.
	 */
	public TextAreaConsole() {
		super();
	}

	@Override
	public boolean getEditable() {
		return false;
	}

}
