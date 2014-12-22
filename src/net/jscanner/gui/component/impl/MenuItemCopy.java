package net.jscanner.gui.component.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Copy" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemCopy extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 2436308992516227238L;

	/**
	 * Creates the "Copy" menu item.
	 */
	public MenuItemCopy() {
		super("Copy");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				new StringSelection(
						JScanner.getInstance(this).getConsoleText()),
						null);
		JOptionPane.showMessageDialog(this, "Successfully copied text to the "
				+ "clipboard!");
	}

}
