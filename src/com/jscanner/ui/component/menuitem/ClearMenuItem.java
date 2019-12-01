package com.jscanner.ui.component.menuitem;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.jscanner.ui.component.ComponentMenuItem;
import com.jscanner.ui.impl.JScannerUI;
import com.jscanner.ui.resource.ResourceManager;

/**
 * The "Clear" menu item.
 * 
 * @author Desmond Jackson
 */
public class ClearMenuItem extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -9132859800034486488L;

	/**
	 * Creates a new "Clear" menu item.
	 */
	public ClearMenuItem() {
		super("Clear");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScannerUI.getInstance(this).getLogger().clear();
	}

	@Override
	public ImageIcon getImageIcon() {
		return ResourceManager.getImage("clear.png");
	}

}
