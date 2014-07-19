package com.jscanner.gui.component.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.jscanner.JScanner;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Copy" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemCopy extends ComponentMenuItem {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 5546993791899934041L;

	/**
	 * Builds the "Copy" menu item.
	 */
	public MenuItemCopy() {
		super("Copy");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(JScanner.getConsoleText()),
				null);
		JOptionPane.showMessageDialog(this,
				"Successfully copied text to the clipboard!");
	}

}
