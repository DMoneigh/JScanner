package net.jscanner.gui.component.impl;

import java.awt.event.ItemEvent;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentCheckBox;

/**
 * The "Always On Top" check box.
 * 
 * @author Desmond Jackson
 */
public class CheckBoxAlwaysOnTop extends ComponentCheckBox {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -501539643845478006L;

	/**
	 * Creates the "Always On Top" check box.
	 */
	public CheckBoxAlwaysOnTop() {
		super("Always On Top");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JScanner.getInstance(this).setAlwaysOnTop(isSelected());
	}

}
