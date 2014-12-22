package net.jscanner.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Represents a button.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentButton extends JButton 
implements ActionListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -7028139142950977615L;
	
	/**
	 * Creates a new button.
	 * 
	 * @param text The text to appear on the button
	 */
	public ComponentButton(String text) {
		super(text);
		addActionListener(this);
	}

}
