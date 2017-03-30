package net.jscanner.ui.component.checkbox;

import java.awt.event.ItemEvent;

import net.jscanner.ui.component.ComponentCheckBox;
import net.jscanner.ui.impl.JScannerUI;

/**
 * The "Resizable" check box.
 * 
 * @author Desmond Jackson
 */
public class ResizableCheckBox extends ComponentCheckBox {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 4529534897986460774L;

	/**
	 * Creates a new "Resizable" check box.
	 */
	public ResizableCheckBox() {
		super("Resizable");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JScannerUI.getInstance(this).setResizable(isSelected());
	}

}
