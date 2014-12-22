package net.jscanner.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Represents a text field.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentTextField extends JTextField 
implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 630091393280182810L;
	
	/**
	 * Creates a new text field.
	 */
	public ComponentTextField() {
		super();
		addActionListener(this);
	}

}
