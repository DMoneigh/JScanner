package net.jscanner.ui.component;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Represents a text field.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTextField extends JTextField implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -1426110709764336436L;
	
	/**
	 * Creates a new text field.
	 */
	public ComponentTextField() {
		addActionListener(this);
	}

}
