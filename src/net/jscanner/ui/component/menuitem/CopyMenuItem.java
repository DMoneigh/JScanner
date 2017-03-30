package net.jscanner.ui.component.menuitem;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import net.jscanner.ui.component.ComponentMenuItem;
import net.jscanner.ui.impl.JScannerUI;
import net.jscanner.ui.resource.ResourceManager;

/**
 * The "Copy" menu item.
 * 
 * @author Desmond Jackson
 */
public class CopyMenuItem extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -1034151796565600807L;

	/**
	 * Creates a new "Copy" menu item.
	 */
	public CopyMenuItem() {
		super("Copy");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				new StringSelection(JScannerUI.getInstance(this).getLogger().getText()),
				null
				);
		JOptionPane.showMessageDialog(this, "Successfully copied text to the clipboard!");
	}

	@Override
	public ImageIcon getImageIcon() {
		return ResourceManager.getImage("copy.png");
	}

}
