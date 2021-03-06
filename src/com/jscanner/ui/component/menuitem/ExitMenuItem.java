package com.jscanner.ui.component.menuitem;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.jscanner.ui.component.ComponentMenuItem;
import com.jscanner.ui.impl.JScannerUI;
import com.jscanner.ui.resource.ResourceManager;

/**
 * The "Exit" menu item.
 * 
 * @author Desmond Jackson
 */
public class ExitMenuItem extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 8620622882088154898L;

	/**
	 * Creates a new "Exit" menu item.
	 */
	public ExitMenuItem() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScannerUI.getInstance(this).dispose();
	}

	@Override
	public ImageIcon getImageIcon() {
		return ResourceManager.getImage("exit.png");
	}

}
