package net.jscanner.gui.component;

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
	private static final long serialVersionUID = -6434833104966065633L;
	
	/**
	 * Creates a new text area.
	 */
	public ComponentTextArea() {
		super();
		setEditable(getEditable());
	}
	
	/**
	 * Gets whether the text area should be editable or not.
	 * 
	 * @return true if the text area should be editable
	 */
	public abstract boolean getEditable();
	
	@Override
	public void append(String string) {
		super.append(string + "\n");
	}
	
}
