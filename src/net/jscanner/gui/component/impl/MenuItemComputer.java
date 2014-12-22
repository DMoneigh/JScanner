package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;

/**
 * The "Computer" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemComputer extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 2214232205409759058L;

	/**
	 * Creates the "Computer" menu item.
	 */
	public MenuItemComputer() {
		super("Computer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JScanner jScanner = JScanner.getInstance(this);
		JOptionPane.showMessageDialog(this,
				"Scanning your computer for archives. This could take a while.");
		int count = 0;
		long startTime = System.currentTimeMillis();
		for (File root : File.listRoots())
			for (File file : FileUtils.listFiles(root, new String[] {
					"class", "jar"}, true)) {
				jScanner.print(file.getAbsolutePath());
				count++;
			}
		jScanner.print("");
		jScanner.print("Found " + count + " archives in " + 
				(System.currentTimeMillis() - startTime) / 1000 + " seconds.");
	}

}
