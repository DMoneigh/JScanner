package com.jscanner.ui.component.checkbox;

import java.awt.event.ItemEvent;

import com.jscanner.ui.component.ComponentCheckBox;
import com.jscanner.ui.impl.JScannerUI;

/**
 * The "Always On Top" check box.
 * 
 * @author Desmond Jackson
 */
public class AlwaysOnTopCheckBox extends ComponentCheckBox {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 9074616104160328386L;

	/**
	 * Creates a new "Always On Top" check box.
	 */
	public AlwaysOnTopCheckBox() {
		super("Always On Top");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JScannerUI.getInstance(this).setAlwaysOnTop(isSelected());
	}

}
