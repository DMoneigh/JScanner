package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;

import net.jscanner.gui.component.ComponentTextField;
import net.jscanner.gui.impl.GuiThreatSelection;

/**
 * The "Search" text field.
 * 
 * @author Desmond Jackson
 */
public class TextFieldSearch extends ComponentTextField {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -7613432038450653598L;

	/**
	 * Creates a new "Search" text field.
	 */
	public TextFieldSearch() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GuiThreatSelection.getInstance(this).search(getText());
	}

}
