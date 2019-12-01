package com.jscanner.ui.component.menuitem;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.jscanner.ui.component.ComponentMenuItem;
import com.jscanner.ui.impl.JScannerUI;
import com.jscanner.ui.resource.ResourceManager;

/**
 * The "New Window" menu item.
 * 
 * @author Desmond Jackson
 */
public class NewWindowMenuItem extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6037010022754292363L;

	/**
	 * Creates a "New Window" menu item.
	 */
	public NewWindowMenuItem() {
		super("New Window");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScannerUI.display();
	}

	@Override
	public ImageIcon getImageIcon() {
		return ResourceManager.getImage("new-window.png");
	}

}
