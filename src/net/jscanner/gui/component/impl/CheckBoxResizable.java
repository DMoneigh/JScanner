package net.jscanner.gui.component.impl;

import java.awt.event.ItemEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentCheckBox;

/**
 * The "Resizable" check box.
 * 
 * @author Desmond Jackson
 */
public class CheckBoxResizable extends ComponentCheckBox {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6133423865699592307L;

	/**
	 * Creates the "Resizable" check box.
	 */
	public CheckBoxResizable() {
		super("Resizable");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JScanner.getInstance(this).setResizable(isSelected());
	}

}
