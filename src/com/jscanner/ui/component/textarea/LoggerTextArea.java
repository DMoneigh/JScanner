package com.jscanner.ui.component.textarea;

import com.jscanner.ui.component.ComponentTextArea;

/**
 * The logger.
 * 
 * @author Desmond Jackson
 */
public class LoggerTextArea extends ComponentTextArea {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -7282367545064619224L;
	
	/**
	 * Logs text.
	 * 
	 * @param text The text
	 */
	public void log(String text) {
		super.append(text);
	}
	
	/**
	 * Clears the text from the area.
	 */
	public void clear() {
		setText("");
	}
	
}
